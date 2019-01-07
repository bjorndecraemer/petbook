package bjorn.petprojects.bpppetbook.services;

import bjorn.petprojects.bpppetbook.model.OwnerGroup;

import java.util.List;

public interface OwnerGroupService extends CrudService<OwnerGroup,Long> {

    OwnerGroup findByPetOwnersTitle(String title);

    List<OwnerGroup> findAllByTitleLike(String title);
}
