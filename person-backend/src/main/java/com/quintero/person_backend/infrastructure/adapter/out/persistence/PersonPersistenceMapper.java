package com.quintero.person_backend.infrastructure.adapter.out.persistence;

import com.quintero.person_backend.domain.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonPersistenceMapper {

    public Person toDomain(PersonJpaEntity entity) {
        return Person.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .fechaNacimiento(entity.getFechaNacimiento())
                .puesto(entity.getPuesto())
                .sueldo(entity.getSueldo())
                .build();
    }

    public PersonJpaEntity toEntity(Person person) {
        return PersonJpaEntity.builder()
                .id(person.getId())
                .nombre(person.getNombre())
                .apellido(person.getApellido())
                .fechaNacimiento(person.getFechaNacimiento())
                .puesto(person.getPuesto())
                .sueldo(person.getSueldo())
                .build();
    }
}
