package com.quintero.person_backend.infrastructure.adapter.in.web.mapper;

import com.quintero.person_backend.domain.model.Person;
import com.quintero.person_backend.infrastructure.adapter.in.web.dto.PersonRequest;
import com.quintero.person_backend.infrastructure.adapter.in.web.dto.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonWebMapper {

    public Person toDomain(PersonRequest request) {
        return Person.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .fechaNacimiento(request.getFechaNacimiento())
                .puesto(request.getPuesto())
                .sueldo(request.getSueldo())
                .build();
    }

    public PersonResponse toResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .nombre(person.getNombre())
                .apellido(person.getApellido())
                .fechaNacimiento(person.getFechaNacimiento())
                .puesto(person.getPuesto())
                .sueldo(person.getSueldo())
                .build();
    }
}
