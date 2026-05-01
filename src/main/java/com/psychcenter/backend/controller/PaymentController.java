package com.psychcenter.backend.controller;

import com.psychcenter.backend.model.entity.Payment;
import com.psychcenter.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public Payment pay(@RequestParam Long bookingId) {
        return service.pay(bookingId);
    }
}