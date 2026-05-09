package com.psychcenter.backend.service.payment.impl;

import com.psychcenter.backend.dto.response.PaymentResponseDto;
import com.psychcenter.backend.mapper.payment.PaymentMapper;
import com.psychcenter.backend.model.entity.booking.Booking;
import com.psychcenter.backend.model.entity.payment.Payment;
import com.psychcenter.backend.model.enums.PaymentStatus;
import com.psychcenter.backend.repository.booking.BookingRepository;
import com.psychcenter.backend.repository.payment.PaymentRepository;
import com.psychcenter.backend.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    @Override
    public PaymentResponseDto pay(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new com.psychcenter.backend.common.exception.base.ResourceNotFoundException("Booking not found"));

        Payment p = Payment.builder()
                .booking(booking)
                .amount(50.0)
                .status(PaymentStatus.SUCCESS)
                .build();

        return mapper.toDto(paymentRepository.save(p));
    }
}