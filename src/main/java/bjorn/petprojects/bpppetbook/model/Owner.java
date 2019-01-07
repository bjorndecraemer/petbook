package bjorn.petprojects.bpppetbook.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Owner {
    private String firstName;
    private String lastName;
    private Date birthDate;
}
