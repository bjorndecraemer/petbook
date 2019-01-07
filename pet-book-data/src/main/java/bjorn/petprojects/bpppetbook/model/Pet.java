package bjorn.petprojects.bpppetbook.model;


import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Pet {
    private String name;
    private Date birthDate;
    public abstract PetType getPetType();
}
