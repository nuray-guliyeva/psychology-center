package com.psychcenter.backend.repository.payment;

import com.psychcenter.backend.dto.response.PaymentResponseDto;
import com.psychcenter.backend.model.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


    @Query(value = "select new com.psychcenter.backend.dto.response.PaymentResponseDto(p.id, p.amount, p.status, b.id) from Payment p join p.booking b")
    public List<PaymentResponseDto> getResponseDtoList();

}