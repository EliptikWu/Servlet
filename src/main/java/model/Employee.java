package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee implements Serializable {
    private Long idEmployee;
    private String name;
    private String email;
    private int telephone;
}
