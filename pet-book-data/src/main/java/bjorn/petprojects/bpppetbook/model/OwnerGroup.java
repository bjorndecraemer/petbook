package bjorn.petprojects.bpppetbook.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "owner_groups")
public class OwnerGroup extends BaseEntity{
    @Builder
    public OwnerGroup(Long id, String title, Set<Owner> owners, Set<Pet> pets) {
        super(id);
        this.title = title;
        this.owners = owners;
        this.pets = pets;
    }

    @Column(name = "owner_group_title")
    private String title;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "ownerGroup")
    private Set<Owner> owners;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerGroup")
    private Set<Pet> pets;

    public int getAmountOfPets(){
        if(pets == null){
            return 0;
        }
        else{
            return pets.size();
        }
    }
}
