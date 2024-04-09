package dtos;

import lombok.Builder;

@Builder
public record ToyDto(Long id,
                     String name,
                     Double price,
                     Integer amount,
                     String category) {
}
