package bjorn.petprojects.bpppetbook.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "owners")
public class Owner extends BaseEntity{

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
