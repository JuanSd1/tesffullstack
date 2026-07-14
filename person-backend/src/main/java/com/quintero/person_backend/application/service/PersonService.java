package com.quintero.person_backend.application.service;

import com.quintero.person_backend.domain.exception.PersonNotFoundException;
import com.quintero.person_backend.domain.model.Person;
import com.quintero.person_backend.domain.port.in.*;
import com.quintero.person_backend.domain.port.out.PersonRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements CreatePersonUseCase, GetPersonUseCase, UpdatePersonUseCase, DeletePersonUseCase, ListPersonsUseCase {

    private final PersonRepositoryPort repository;

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public Person getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person update(Long id, Person person) {
        if (!repository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
        person.setId(id);
        return repository.save(person);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Person> listAll() {
        return repository.findAll();
    }
}
