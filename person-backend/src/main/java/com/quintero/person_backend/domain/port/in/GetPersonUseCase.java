package com.quintero.person_backend.domain.port.in;

import com.quintero.person_backend.domain.model.Person;

public interface GetPersonUseCase {
    Person getById(Long id);
}
