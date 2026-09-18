package com.cfs.BMS.service;

import com.cfs.BMS.dto.PaymentOrderRequest;
import com.cfs.BMS.dto.PaymentOrderResponse;
import com.cfs.BMS.entity.Booking;
import com.cfs.BMS.entity.Payment;
import com.cfs.BMS.enums.BookingStatus;
import com.cfs.BMS.repository.BookingRepository;
import com.cfs.BMS.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    // =========================
    // CREATE RAZORPAY ORDER
    // =========================

    @Transactional
    public PaymentOrderResponse createOrder(PaymentOrderRequest request)
            throws Exception {

        // 1. Find booking
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() ->
                        new RuntimeException("Booking not found with id: "
                                + request.getBookingId()));

        // 2. Get booking amount
        double amount = booking.getTotalPrice();

        // 3. Convert rupees to paise
        long amountInPaise = Math.round(amount * 100);

        // 4. Create Razorpay client
        RazorpayClient razorpayClient =
                new RazorpayClient(razorpayKeyId, razorpayKeySecret);

        // 5. Create Razorpay order
        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", "INR");
        orderRequest.put(
                "receipt",
                "booking_" + booking.getId()
        );

        Order razorpayOrder =
                razorpayClient.orders.create(orderRequest);

        // 6. Save payment information
        Payment payment = Payment.builder()
                .bookingId(booking.getId())
                .razorpayOrderId(razorpayOrder.get("id"))
                .amount(amount)
                .status("CREATED")
                .build();

        paymentRepository.save(payment);

        // 7. Return order information
        return new PaymentOrderResponse(
                booking.getId(),
                razorpayOrder.get("id"),
                amountInPaise,
                "INR"
        );
    }


    // =========================
    // VERIFY RAZORPAY PAYMENT
    // =========================

    @Transactional
    public boolean verifyPayment(
            String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature) throws Exception {

        // 1. Generate signature
        String generatedSignature =
                Utils.getHash(
                        razorpayOrderId + "|" + razorpayPaymentId,
                        razorpayKeySecret
                );

        // 2. Compare signatures
        boolean isValid =
                generatedSignature.equals(razorpaySignature);

        // 3. If signature is invalid
        if (!isValid) {
            return false;
        }

        // 4. Find payment
        Payment payment = paymentRepository
                .findByRazorpayOrderId(razorpayOrderId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        // 5. Update payment information
        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setRazorpaySignature(razorpaySignature);
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);

        // 6. Find booking
        Booking booking = bookingRepository
                .findById(payment.getBookingId())
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        // 7. Confirm booking
        booking.setStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);

// Send booking confirmation email
        emailService.sendBookingConfirmation(booking);
        return true;
    }
}