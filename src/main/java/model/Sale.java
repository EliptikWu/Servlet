package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Sale {
    private Long idSale;
    private Long idToy;
    private Long idClient;
    private Long idEmployee;
}
