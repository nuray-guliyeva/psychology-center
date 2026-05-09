package com.psychcenter.backend.dto.response;

import com.psychcenter.backend.model.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDto {

    public PaymentResponseDto(Long id, Double amount, PaymentStatus status, Long bookingId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.bookingId = bookingId;
    }
    private Long id;
    private Double amount;
    private PaymentStatus status;



    private Long bookingId;
}