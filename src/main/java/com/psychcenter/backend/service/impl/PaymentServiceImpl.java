package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.common.exception.payment.PaymentAlreadyExistsException;
import com.psychcenter.backend.dto.response.PaymentResponseDto;
import com.psychcenter.backend.mapper.PaymentMapper;
import com.psychcenter.backend.model.entity.Booking;
import com.psychcenter.backend.model.entity.Payment;
import com.psychcenter.backend.model.enums.PaymentStatus;
import com.psychcenter.backend.repository.BookingRepository;
import com.psychcenter.backend.repository.PaymentRepository;
import com.psychcenter.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto pay(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found")
                );

        if (paymentRepository.existsByBookingId(bookingId)) {
            throw new PaymentAlreadyExistsException(
                    "Booking already paid"
            );
        }

        Payment p = Payment.builder()
                .booking(booking)
                .amount(50.0)
                .status(PaymentStatus.SUCCESS)
                .build();

        return paymentMapper.toDto(paymentRepository.save(p));
    }
}