package com.cfs.BMS.controller;

import com.cfs.BMS.dto.PaymentOrderRequest;
import com.cfs.BMS.dto.PaymentOrderResponse;
import com.cfs.BMS.dto.PaymentVerifyRequest;
import com.cfs.BMS.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Create Razorpay Order
    @PostMapping("/create-order")
    public ResponseEntity<PaymentOrderResponse> createOrder(
            @RequestBody PaymentOrderRequest request) throws Exception {

        return ResponseEntity.ok(
                paymentService.createOrder(request)
        );
    }

    // Verify Razorpay Payment
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(
            @RequestBody PaymentVerifyRequest request)
            throws Exception {

        boolean verified = paymentService.verifyPayment(
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature()
        );

        if (verified) {
            return ResponseEntity.ok(
                    "Payment verified successfully. Booking confirmed."
            );
        }

        return ResponseEntity.badRequest().body(
                "Payment verification failed."
        );
    }
}