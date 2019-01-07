package bjorn.petprojects.bpppetbook.services.map;

import bjorn.petprojects.bpppetbook.model.Owner;
import bjorn.petprojects.bpppetbook.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
public class OwnerMapServiceImpl extends AbstractMapService<Owner,Long> implements OwnerService {
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return this
                .findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public  List<Owner> findByFirstName(String lastName) {
        return this
                .findAll()
                .stream()
                .filter(owner -> owner.getFirstName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Owner> findAllByFirstNameLike(String namePart) {
        return this
                .findAll()
                .stream()
                .filter(owner -> owner.getFirstName().matches(".*"+namePart+".*"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Owner> findAllByLastNameLike(String namePart) {
        return this
                .findAll()
                .stream()
                .filter(owner -> owner.getLastName().matches(".*"+namePart+".*"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Owner> findAllByFullNameLike(String namePart) {
        return this
                .findAll()
                .stream()
                .filter(owner -> owner.getFullName().matches(".*"+namePart+".*"))
                .collect(Collectors.toList());
    }
}
