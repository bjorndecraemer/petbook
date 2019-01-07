package bjorn.petprojects.bpppetbook.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
public class Dog extends Pet {

    public Dog() {
    }

    public Dog(Long id,String name, Date birthDate) {
        super(id,name, birthDate);
    }

    @Override
    public PetType getPetType() {
        return PetType.DOG;
    }
}
