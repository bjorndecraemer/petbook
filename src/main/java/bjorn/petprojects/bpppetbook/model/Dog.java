package bjorn.petprojects.bpppetbook.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dog extends Pet {
    @Override
    public PetType getPetType() {
        return PetType.DOG;
    }
}
