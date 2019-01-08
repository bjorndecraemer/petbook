package bjorn.petprojects.bpppetbook.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Builder
    public Pet(Long id, String name, Date birthDate, PetType petType, OwnerGroup ownerGroup) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.ownerGroup = ownerGroup;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private Date birthDate;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_group_id")
    private OwnerGroup ownerGroup;
}
