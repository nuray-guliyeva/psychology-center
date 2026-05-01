package com.psychcenter.backend.service;

import com.psychcenter.backend.model.entity.Payment;

public interface PaymentService {
    Payment pay(Long bookingId);
}