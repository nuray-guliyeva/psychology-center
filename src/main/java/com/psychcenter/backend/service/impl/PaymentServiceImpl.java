package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.common.exception.payment.PaymentAlreadyExistsException;
import com.psychcenter.backend.dto.response.PaymentResponseDto;
import com.psychcenter.backend.mapper.PaymentMapper;
import com.psychcenter.backend.model.entity.Booking;
import com.psychcenter.backend.model.entity.Payment;
import com.psychcenter.backend.model.entity.User;
import com.psychcenter.backend.model.enums.PaymentStatus;
import com.psychcenter.backend.repository.BookingRepository;
import com.psychcenter.backend.repository.PaymentRepository;
import com.psychcenter.backend.repository.UserRepository;
import com.psychcenter.backend.security.util.SecurityUtils;
import com.psychcenter.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;

    @Override
    public PaymentResponseDto pay(Long bookingId) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found")
                );

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException(
                    "You cannot pay for another user's booking"
            );
        }

        Payment payment = Payment.builder()
                .booking(booking)
                .amount(booking.getAmount())
                .status(PaymentStatus.SUCCESS)
                .build();

        try {

            Payment saved = paymentRepository.save(payment);

            return paymentMapper.toDto(saved);

        } catch (DataIntegrityViolationException e) {

            throw new PaymentAlreadyExistsException(
                    "Booking already paid"
            );
        }
    }

}