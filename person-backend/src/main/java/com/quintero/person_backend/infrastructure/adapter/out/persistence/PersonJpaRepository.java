package com.quintero.person_backend.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonJpaEntity, Long> {
}
