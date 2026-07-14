package com.quintero.person_backend.domain.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Persona no encontrada con id: " + id);
    }
}
