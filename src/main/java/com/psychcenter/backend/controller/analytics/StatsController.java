package com.psychcenter.backend.controller.analytics;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.response.StatsDto;
import com.psychcenter.backend.service.analytics.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stats")
@RequiredArgsConstructor
public class StatsController {

    private final AnalyticsService service;

    @GetMapping
    public ApiResponse<StatsDto> stats() {
        return ApiResponse.success(
                service.stats(),
                "System statistics"
        );
    }
}