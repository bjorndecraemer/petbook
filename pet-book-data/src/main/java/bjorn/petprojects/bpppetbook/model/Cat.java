package bjorn.petprojects.bpppetbook.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Cat extends Pet {

    public Cat(){
        super();
    }

    public Cat(String name, Date birthDate) {
        super(name, birthDate);
    }

    @Override
    public PetType getPetType() {
        return PetType.CAT;
    }
}
