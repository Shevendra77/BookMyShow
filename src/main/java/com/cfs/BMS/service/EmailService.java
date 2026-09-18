package com.cfs.BMS.service;

import com.cfs.BMS.entity.Booking;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendBookingConfirmation(Booking booking)
            throws MessagingException {

        // ==============================
        // BASIC DETAILS
        // ==============================

        String email =
                booking.getUser().getEmail();

        String customerName =
                booking.getUser().getName();

        String movieName =
                booking.getShow()
                        .getMovie()
                        .getTitle();

        String theaterName =
                booking.getShow()
                        .getScreen()
                        .getTheater()
                        .getName();

        String screenName =
                booking.getShow()
                        .getScreen()
                        .getName();

        String showDate =
                String.valueOf(
                        booking.getShow().getShowDate()
                );

        String startTime =
                String.valueOf(
                        booking.getShow().getStartTime()
                );


        // ==============================
        // SEAT DETAILS
        // ==============================

        StringBuilder seats =
                new StringBuilder();

        booking.getSeats().forEach(seat -> {

            seats.append(
                    seat.getSeatNumber()
            );

            seats.append(", ");

        });

        if (seats.length() > 0) {

            seats.setLength(
                    seats.length() - 2
            );

        }


        // ==============================
        // HTML EMAIL
        // ==============================

        String htmlContent =
                "<!DOCTYPE html>" +

                        "<html>" +

                        "<head>" +

                        "<meta charset='UTF-8'>" +

                        "<meta name='viewport' " +
                        "content='width=device-width, initial-scale=1.0'>" +

                        "<title>Movie Ticket Confirmation</title>" +

                        "</head>" +

                        "<body style='" +
                        "margin:0;" +
                        "padding:0;" +
                        "background:#f4f5f7;" +
                        "font-family:Arial,Helvetica,sans-serif;" +
                        "'>" +


                        // ==============================
                        // MAIN CONTAINER
                        // ==============================

                        "<div style='" +
                        "max-width:650px;" +
                        "margin:30px auto;" +
                        "background:#ffffff;" +
                        "border-radius:12px;" +
                        "overflow:hidden;" +
                        "box-shadow:0 4px 15px rgba(0,0,0,0.08);" +
                        "'>" +


                        // ==============================
                        // HEADER
                        // ==============================

                        "<div style='" +
                        "background:#e94560;" +
                        "padding:25px;" +
                        "text-align:center;" +
                        "color:#ffffff;" +
                        "'>" +

                        "<h1 style='" +
                        "margin:0;" +
                        "font-size:28px;" +
                        "letter-spacing:1px;" +
                        "'>" +
                        "🎬 BookMyShow" +
                        "</h1>" +

                        "<p style='" +
                        "margin:8px 0 0;" +
                        "font-size:15px;" +
                        "'>" +
                        "Movie Ticket Confirmed" +
                        "</p>" +

                        "</div>" +


                        // ==============================
                        // SUCCESS MESSAGE
                        // ==============================

                        "<div style='" +
                        "padding:25px 30px 10px;" +
                        "text-align:center;" +
                        "'>" +

                        "<div style='" +
                        "width:55px;" +
                        "height:55px;" +
                        "margin:0 auto 12px;" +
                        "border-radius:50%;" +
                        "background:#e8f7ee;" +
                        "color:#16a34a;" +
                        "font-size:30px;" +
                        "line-height:55px;" +
                        "'>" +
                        "✓" +
                        "</div>" +

                        "<h2 style='" +
                        "margin:0;" +
                        "color:#222222;" +
                        "font-size:22px;" +
                        "'>" +
                        "Booking Successful!" +
                        "</h2>" +

                        "<p style='" +
                        "color:#666666;" +
                        "font-size:14px;" +
                        "line-height:1.6;" +
                        "'>" +

                        "Hello " +
                        escapeHtml(customerName) +
                        ",<br>" +

                        "Your movie ticket has been booked successfully." +

                        "</p>" +

                        "</div>" +


                        // ==============================
                        // MOVIE TICKET
                        // ==============================

                        "<div style='" +
                        "margin:20px 30px;" +
                        "border:1px solid #e5e7eb;" +
                        "border-radius:10px;" +
                        "overflow:hidden;" +
                        "'>" +


                        // Movie header

                        "<div style='" +
                        "background:#1f2937;" +
                        "color:#ffffff;" +
                        "padding:15px 20px;" +
                        "'>" +

                        "<h2 style='" +
                        "margin:0;" +
                        "font-size:20px;" +
                        "'>" +

                        "🎥 " +
                        escapeHtml(movieName) +

                        "</h2>" +

                        "</div>" +


                        // Movie details

                        "<div style='padding:20px;'>" +

                        "<table width='100%' " +
                        "cellpadding='8' " +
                        "cellspacing='0' " +
                        "style='font-size:14px;color:#444444;'>" +


                        "<tr>" +

                        "<td>" +
                        "<strong>🏢 Theater</strong>" +
                        "</td>" +

                        "<td align='right'>" +
                        escapeHtml(theaterName) +
                        "</td>" +

                        "</tr>" +


                        "<tr>" +

                        "<td>" +
                        "<strong>🖥 Screen</strong>" +
                        "</td>" +

                        "<td align='right'>" +
                        escapeHtml(screenName) +
                        "</td>" +

                        "</tr>" +


                        "<tr>" +

                        "<td>" +
                        "<strong>📅 Date</strong>" +
                        "</td>" +

                        "<td align='right'>" +
                        escapeHtml(showDate) +
                        "</td>" +

                        "</tr>" +


                        "<tr>" +

                        "<td>" +
                        "<strong>🕐 Time</strong>" +
                        "</td>" +

                        "<td align='right'>" +
                        escapeHtml(startTime) +
                        "</td>" +

                        "</tr>" +


                        "<tr>" +

                        "<td>" +
                        "<strong>💺 Seats</strong>" +
                        "</td>" +

                        "<td align='right' " +
                        "style='color:#e94560;font-weight:bold;'>" +

                        escapeHtml(seats.toString()) +

                        "</td>" +

                        "</tr>" +


                        "</table>" +

                        "</div>" +

                        "</div>" +


                        // ==============================
                        // PAYMENT DETAILS
                        // ==============================

                        "<div style='" +
                        "margin:20px 30px;" +
                        "padding:20px;" +
                        "background:#f8fafc;" +
                        "border-radius:10px;" +
                        "'>" +

                        "<h3 style='" +
                        "margin:0 0 15px;" +
                        "color:#222222;" +
                        "font-size:17px;" +
                        "'>" +

                        "💳 Payment Details" +

                        "</h3>" +


                        "<table width='100%' " +
                        "cellpadding='6' " +
                        "cellspacing='0' " +
                        "style='font-size:14px;'>" +


                        "<tr>" +

                        "<td style='color:#666666;'>" +
                        "Booking ID" +
                        "</td>" +

                        "<td align='right'>" +

                        "<strong>#" +
                        booking.getId() +
                        "</strong>" +

                        "</td>" +

                        "</tr>" +


                        "<tr>" +

                        "<td style='color:#666666;'>" +
                        "Amount Paid" +
                        "</td>" +

                        "<td align='right'>" +

                        "<strong style='" +
                        "color:#16a34a;" +
                        "font-size:17px;" +
                        "'>" +

                        "₹" +
                        String.format(
                                "%.2f",
                                booking.getTotalPrice()
                        ) +

                        "</strong>" +

                        "</td>" +

                        "</tr>" +


                        "<tr>" +

                        "<td style='color:#666666;'>" +
                        "Payment Status" +
                        "</td>" +

                        "<td align='right'>" +

                        "<span style='" +
                        "display:inline-block;" +
                        "padding:5px 10px;" +
                        "border-radius:20px;" +
                        "background:#dcfce7;" +
                        "color:#15803d;" +
                        "font-weight:bold;" +
                        "font-size:12px;" +
                        "'>" +

                        "✓ CONFIRMED" +

                        "</span>" +

                        "</td>" +

                        "</tr>" +


                        "</table>" +

                        "</div>" +


                        // ==============================
                        // IMPORTANT MESSAGE
                        // ==============================

                        "<div style='" +
                        "margin:20px 30px;" +
                        "padding:15px;" +
                        "border-left:4px solid #e94560;" +
                        "background:#fff5f7;" +
                        "color:#555555;" +
                        "font-size:13px;" +
                        "line-height:1.6;" +
                        "'>" +

                        "<strong style='color:#e94560;'>" +
                        "Important" +
                        "</strong>" +

                        "<br>" +

                        "Please arrive at the theater " +
                        "10–15 minutes before the show." +

                        "</div>" +


                        // ==============================
                        // FOOTER
                        // ==============================

                        "<div style='" +
                        "background:#f1f3f5;" +
                        "padding:20px;" +
                        "text-align:center;" +
                        "color:#777777;" +
                        "font-size:12px;" +
                        "'>" +

                        "<p style='margin:0 0 5px;'>" +

                        "Thank you for booking with " +

                        "<strong>BookMyShow</strong>." +

                        "</p>" +

                        "<p style='margin:0;'>" +

                        "Enjoy your movie! 🍿🎬" +

                        "</p>" +

                        "</div>" +


                        "</div>" +

                        "</body>" +

                        "</html>";


        // ==============================
        // SEND EMAIL
        // ==============================

        MimeMessage message =
                mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(
                        message,
                        true,
                        "UTF-8"
                );

        helper.setTo(email);

        helper.setSubject(
                "🎬 Movie Ticket Confirmed - BookMyShow"
        );

        helper.setText(
                htmlContent,
                true
        );

        mailSender.send(message);
    }


    // ==============================
    // HTML ESCAPE
    // ==============================

    private String escapeHtml(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
