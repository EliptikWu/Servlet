package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class    Toy implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private Integer amount;
    private String category;

}
