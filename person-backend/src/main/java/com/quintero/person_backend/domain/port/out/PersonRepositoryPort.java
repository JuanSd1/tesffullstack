package com.quintero.person_backend.domain.port.out;

import com.quintero.person_backend.domain.model.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepositoryPort {
    Person save(Person person);
    Optional<Person> findById(Long id);
    List<Person> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
