package dtos;

import lombok.Builder;

import java.sql.Date;

@Builder
public record ClientDto(Long Idclient,
        String name,
        String email,
        Integer telephone,
        Date birthDate) {
        }

