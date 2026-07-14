package com.quintero.person_backend.infrastructure.adapter.in.web.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String msg;
    private List<T> data;
}
