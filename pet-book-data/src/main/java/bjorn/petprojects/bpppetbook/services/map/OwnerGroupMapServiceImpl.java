package bjorn.petprojects.bpppetbook.services.map;

import bjorn.petprojects.bpppetbook.model.Owner;
import bjorn.petprojects.bpppetbook.model.OwnerGroup;
import bjorn.petprojects.bpppetbook.model.Pet;
import bjorn.petprojects.bpppetbook.services.OwnerGroupService;
import bjorn.petprojects.bpppetbook.services.OwnerService;
import bjorn.petprojects.bpppetbook.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
public class OwnerGroupMapServiceImpl extends AbstractMapService<OwnerGroup,Long> implements OwnerGroupService {

    private final PetService petService;
    private final OwnerService ownerService;

    public OwnerGroupMapServiceImpl(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @Override
    public Set<OwnerGroup> findAll() {
        return super.findAll();
    }

    @Override
    public OwnerGroup findById(Long id) {
        return super.findById(id);
    }

    @Override
    public OwnerGroup save(OwnerGroup ownerGroup) {
        if(ownerGroup != null){
            if(ownerGroup.getPets() != null){
                ownerGroup.getPets().forEach(pet -> {
                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            if(ownerGroup.getOwners() != null){
                ownerGroup.getOwners().forEach(owner -> {
                    if(owner.getId() == null){
                        Owner savedOwner = ownerService.save(owner);
                        owner.setId(savedOwner.getId());
                    }
                });
            }
            return super.save(ownerGroup);
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(OwnerGroup ownerGroup) {
        super.delete(ownerGroup);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public OwnerGroup findByPetOwnersTitle(String title) {
        return this
                .findAll()
                .stream()
                .filter(ownerGroup -> ownerGroup.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<OwnerGroup> findAllByTitleLike(String title) {
        return this
                .findAll()
                .stream()
                .filter(ownerGroup -> ownerGroup.getTitle().matches(".*"+title+".*"))
                .collect(Collectors.toList());
    }
}
