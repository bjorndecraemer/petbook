package bjorn.petprojects.bpppetbook.repositories;

import bjorn.petprojects.bpppetbook.model.OwnerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnersRepository extends JpaRepository<OwnerGroup,Long> {
}
