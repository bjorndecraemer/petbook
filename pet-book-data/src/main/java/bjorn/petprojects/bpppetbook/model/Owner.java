package bjorn.petprojects.bpppetbook.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "owners")
public class Owner extends BaseEntity{

    @Builder
    public Owner(Long id, String firstName, String lastName, Date birthDate, OwnerGroup ownerGroup) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.ownerGroup = ownerGroup;
    }

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private Date birthDate;

    public String getFullName(){
        return firstName +" "+ lastName;
    }
    @ManyToOne
    @JoinColumn(name = "owner_group_id")
    private OwnerGroup ownerGroup;
}
