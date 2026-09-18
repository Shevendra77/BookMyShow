
package com.cfs.BMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentOrderResponse {

    private Long bookingId;

    private String orderId;

    private Long amount;

    private String currency;
}

