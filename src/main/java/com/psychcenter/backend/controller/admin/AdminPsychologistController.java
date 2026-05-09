package com.psychcenter.backend.controller.admin;

import com.psychcenter.backend.service.psychologist.PsychologistService;
import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/psychologists")
@RequiredArgsConstructor
public class AdminPsychologistController {

    private final PsychologistService service;

    @PostMapping
    public Object create(@RequestBody @Valid PsychologistRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id,
                         @RequestBody PsychologistRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}