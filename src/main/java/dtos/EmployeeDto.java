package dtos;

import lombok.Builder;

@Builder
public record EmployeeDto(Long idEmployee,
                          String name,
                          String email,
                          Integer telephone) {
}
