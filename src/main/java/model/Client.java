package model;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Client implements Serializable {

    private Long idClient;
    private String name;
    private String email;
    private int telephone;
    private Date birthDate; //The birthday will be used so that when the customer has a birthday,
                            // they have offers throughout the store on certain products
}
