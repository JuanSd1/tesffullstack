package com.quintero.person_backend.infrastructure.adapter.out.persistence;

import com.quintero.person_backend.domain.model.Person;
import com.quintero.person_backend.domain.port.out.PersonRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository jpaRepository;
    private final PersonPersistenceMapper mapper;

    @Override
    public Person save(Person person) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(person)));
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Person> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
