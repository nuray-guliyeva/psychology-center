package com.psychcenter.backend.service.service;

import com.psychcenter.backend.dto.response.ServiceResponseDto;
import com.psychcenter.backend.model.entity.service.ServiceEntity;

import java.util.List;

public interface ServiceService {

    ServiceResponseDto create(ServiceEntity s);

    List<ServiceResponseDto> getAll();
}