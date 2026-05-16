package com.psychcenter.backend.mapper;

import com.psychcenter.backend.dto.request.ServiceCreateRequestDto;
import com.psychcenter.backend.dto.response.ServiceResponseDto;
import com.psychcenter.backend.model.entity.ServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public ServiceEntity toEntity(ServiceCreateRequestDto dto) {

        return ServiceEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .audience(dto.getAudience())
                .format(dto.getFormat())
                .price(dto.getPrice())
                .build();
    }

    public ServiceResponseDto toDto(ServiceEntity s) {

        return ServiceResponseDto.builder()
                .id(s.getId())
                .title(s.getTitle())
                .description(s.getDescription())
                .audience(s.getAudience())
                .format(s.getFormat())
                .price(s.getPrice())
                .build();
    }
}