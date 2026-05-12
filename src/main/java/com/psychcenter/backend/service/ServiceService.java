package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.response.ServiceResponseDto;
import com.psychcenter.backend.model.entity.ServiceEntity;

import java.util.List;

public interface ServiceService {

    ServiceResponseDto create(ServiceEntity s);

    List<ServiceResponseDto> getAll();
}