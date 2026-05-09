package com.psychcenter.backend.service.payment;

import com.psychcenter.backend.dto.response.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto pay(Long bookingId);
}