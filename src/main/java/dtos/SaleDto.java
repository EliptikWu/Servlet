package dtos;

import lombok.Builder;

@Builder
public record SaleDto(Long idSale,
                      Long idToy,
                      Long idClient,
                      Long idEmployee) {
}
