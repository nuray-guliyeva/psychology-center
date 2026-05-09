package com.psychcenter.backend.mapper.payment;

import com.psychcenter.backend.dto.response.PaymentResponseDto;
import com.psychcenter.backend.model.entity.payment.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDto toDto(Payment p) {
        return PaymentResponseDto.builder()
                .id(p.getId())
                .amount(p.getAmount())
                .status(p.getStatus())
                .bookingId(p.getBooking().getId())
                .build();
    }
}