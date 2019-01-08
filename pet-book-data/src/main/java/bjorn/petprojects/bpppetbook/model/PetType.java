package bjorn.petprojects.bpppetbook.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity{

    @Column(name = "type_name")
    private String name;

    @Builder
    public PetType(Long id, String name){
        super(id);
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
