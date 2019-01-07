package bjorn.petprojects.bpppetbook.services;

import bjorn.petprojects.bpppetbook.model.Pet;

import java.util.List;

public interface PetService extends CrudService<Pet,Long>{
    List<Pet> findByName(String lastName);
    List<Pet> findAllByNameLike(String namePart);
}
