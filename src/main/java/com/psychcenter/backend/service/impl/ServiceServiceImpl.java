package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.request.ServiceCreateRequestDto;
import com.psychcenter.backend.dto.response.ServiceResponseDto;
import com.psychcenter.backend.mapper.ServiceMapper;
import com.psychcenter.backend.model.entity.ServiceEntity;
import com.psychcenter.backend.repository.ServiceRepository;
import com.psychcenter.backend.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    @Transactional
    public ServiceResponseDto create(ServiceCreateRequestDto dto) {

        ServiceEntity entity = serviceMapper.toEntity(dto);

        return serviceMapper.toDto(
                serviceRepository.save(entity)
        );
    }

    @Override
    public List<ServiceResponseDto> getAll() {

        return serviceRepository.findAll()
                .stream()
                .map(serviceMapper::toDto)
                .toList();
    }
}