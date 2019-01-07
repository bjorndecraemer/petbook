package bjorn.petprojects.bpppetbook.model.repositories;

import bjorn.petprojects.bpppetbook.model.PetOwners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnersRepository extends JpaRepository<PetOwners,Long> {
}
