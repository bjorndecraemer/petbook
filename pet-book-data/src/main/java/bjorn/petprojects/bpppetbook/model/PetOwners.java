package bjorn.petprojects.bpppetbook.model;


import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PetOwners {
    private String name;
    private ArrayList<Pet> pets;
}
