package com.quintero.person_backend.infrastructure.adapter.in.web;

import com.quintero.person_backend.domain.model.Person;
import com.quintero.person_backend.domain.port.in.*;
import com.quintero.person_backend.infrastructure.adapter.in.web.dto.ApiResponse;
import com.quintero.person_backend.infrastructure.adapter.in.web.dto.PersonRequest;
import com.quintero.person_backend.infrastructure.adapter.in.web.dto.PersonResponse;
import com.quintero.person_backend.infrastructure.adapter.in.web.mapper.PersonWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final GetPersonUseCase getPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;
    private final ListPersonsUseCase listPersonsUseCase;
    private final PersonWebMapper mapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PersonResponse>> listAll() {
        List<PersonResponse> persons = listPersonsUseCase.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.<PersonResponse>builder()
                .status(true)
                .msg("Personas obtenidas correctamente")
                .data(persons)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonResponse>> getById(@PathVariable Long id) {
        PersonResponse response = mapper.toResponse(getPersonUseCase.getById(id));
        return ResponseEntity.ok(ApiResponse.<PersonResponse>builder()
                .status(true)
                .msg("Persona encontrada")
                .data(List.of(response))
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PersonResponse>> create(@Valid @RequestBody PersonRequest request) {
        Person created = createPersonUseCase.create(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PersonResponse>builder()
                .status(true)
                .msg("Persona creada correctamente")
                .data(List.of(mapper.toResponse(created)))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonResponse>> update(@PathVariable Long id, @Valid @RequestBody PersonRequest request) {
        Person updated = updatePersonUseCase.update(id, mapper.toDomain(request));
        return ResponseEntity.ok(ApiResponse.<PersonResponse>builder()
                .status(true)
                .msg("Persona actualizada correctamente")
                .data(List.of(mapper.toResponse(updated)))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        deletePersonUseCase.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .status(true)
                .msg("Persona eliminada correctamente")
                .data(List.of())
                .build());
    }
}
