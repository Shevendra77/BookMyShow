
        package com.cfs.BMS.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;

    private Double amount;

    private String status;
}

