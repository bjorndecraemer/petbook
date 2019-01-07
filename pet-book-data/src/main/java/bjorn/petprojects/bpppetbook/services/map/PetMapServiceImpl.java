package bjorn.petprojects.bpppetbook.services.map;

import bjorn.petprojects.bpppetbook.model.Pet;
import bjorn.petprojects.bpppetbook.services.PetService;
import bjorn.petprojects.bpppetbook.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
public class PetMapServiceImpl extends AbstractMapService<Pet,Long> implements PetService {

    PetTypeService petTypeService;

    public PetMapServiceImpl(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        if(pet != null){
            if(pet.getPetType() != null && pet.getPetType().getId() != null){
                pet.setPetType(petTypeService.save(pet.getPetType()));
            }
            else{
                throw new RuntimeException("Pet type is required!");
            }
            if(pet.getId() == null){
                Pet savedPet = super.save(pet);
                pet.setId(savedPet.getId());
                return pet;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Pet> findByName(String name) {
        return this
                .findAll()
                .stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> findAllByNameLike(String namePart) {
        return this
                .findAll()
                .stream()
                .filter(pet -> pet.getName().matches(".*"+namePart+".*"))
                .collect(Collectors.toList());
    }
}
