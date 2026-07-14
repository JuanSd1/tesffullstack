package com.quintero.person_backend.domain.port.in;

import com.quintero.person_backend.domain.model.Person;

public interface UpdatePersonUseCase {
    Person update(Long id, Person person);
}
