package com.quintero.person_backend.domain.port.in;

import com.quintero.person_backend.domain.model.Person;
import java.util.List;

public interface ListPersonsUseCase {
    List<Person> listAll();
}
