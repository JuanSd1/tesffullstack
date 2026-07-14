package com.quintero.person_backend.infrastructure.adapter.in.web.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String puesto;
    private BigDecimal sueldo;
}
