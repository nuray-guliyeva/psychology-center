package com.psychcenter.backend.controller;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.response.PaymentResponseDto;
import com.psychcenter.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ApiResponse<PaymentResponseDto> pay(@RequestParam Long bookingId) {
        return ApiResponse.success(service.pay(bookingId), "Payment successful");
    }
}