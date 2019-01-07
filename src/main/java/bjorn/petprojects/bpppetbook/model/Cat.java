package bjorn.petprojects.bpppetbook.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cat extends Pet {
    @Override
    public PetType getPetType() {
        return PetType.CAT;
    }
}
