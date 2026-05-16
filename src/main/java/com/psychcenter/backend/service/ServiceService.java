package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.ServiceCreateRequestDto;
import com.psychcenter.backend.dto.response.ServiceResponseDto;

import java.util.List;

public interface ServiceService {

    ServiceResponseDto create(ServiceCreateRequestDto dto);

    List<ServiceResponseDto> getAll();
}