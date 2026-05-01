package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.model.entity.*;
import com.psychcenter.backend.repository.*;
import com.psychcenter.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Payment pay(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow();

        Payment p = Payment.builder()
                .booking(booking)
                .amount(50.0)
                .status("SUCCESS")
                .build();

        return paymentRepository.save(p);
    }
}