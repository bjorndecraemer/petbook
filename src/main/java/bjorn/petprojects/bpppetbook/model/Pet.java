package bjorn.petprojects.bpppetbook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public abstract class Pet {
    private String name;
    private Date birthDate;
    public abstract PetType getPetType();
}
