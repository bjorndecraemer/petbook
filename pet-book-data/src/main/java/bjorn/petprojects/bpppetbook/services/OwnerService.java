package bjorn.petprojects.bpppetbook.services;

import bjorn.petprojects.bpppetbook.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner,Long> {
    List<Owner> findByLastName(String lastName);
    List<Owner> findAllByFirstNameLike(String namePart);
    List<Owner> findByFirstName(String lastName);
    List<Owner> findAllByLastNameLike(String namePart);
    List<Owner> findAllByFullNameLike(String namePart);
}
