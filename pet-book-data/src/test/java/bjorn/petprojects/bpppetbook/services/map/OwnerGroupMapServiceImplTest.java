package bjorn.petprojects.bpppetbook.services.map;

import bjorn.petprojects.bpppetbook.model.OwnerGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerGroupMapServiceImplTest {

    OwnerGroupMapServiceImpl ownerGroupMapService;

    final Long ownerGroupId = 1L;
    final String DEFAULT_TITLE = "Poezen groep";
    final String DEFAULT_TITLE_PART = "oez";

    @BeforeEach
    void setUp() {
        ownerGroupMapService = new OwnerGroupMapServiceImpl(new PetMapServiceImpl(new PetTypeMapServiceImpl()), new OwnerMapServiceImpl());
        ownerGroupMapService.save(OwnerGroup.builder().id(ownerGroupId).title(DEFAULT_TITLE).build());
    }

    @Test
    void findAll() {
        Set<OwnerGroup> ownerGroupSet = ownerGroupMapService.findAll();
        assertEquals(1,ownerGroupSet.size());
    }

    @Test
    void findById() {
        OwnerGroup ownerGroup = ownerGroupMapService.findById(ownerGroupId);
        assertEquals(ownerGroupId,ownerGroup.getId());
    }

    @Test
    void saveExistingId() {
        String newTitle = "NEW TITLE";
        OwnerGroup ownerGroup2 = OwnerGroup.builder().id(ownerGroupId).title(newTitle).build();
        OwnerGroup newOwnerGroup = ownerGroupMapService.save(ownerGroup2);
        //Total Should still be 1
        assertEquals(1,ownerGroupMapService.findAll().size());
        //returned OwnerGroup should have same DEFAULT_TITLE and ID
        assertEquals(newTitle, newOwnerGroup.getTitle());
        assertEquals(ownerGroupId, newOwnerGroup.getId());
        // Find on DB by ID should return the same entry
        OwnerGroup retrievedOwnerGroup = ownerGroupMapService.findById(ownerGroupId);
        assertEquals(newTitle, retrievedOwnerGroup.getTitle());
    }

    @Test
    void saveNoId() {
        String newTitle = "NEW TITLE";
        OwnerGroup ownerGroup2 = OwnerGroup.builder().title(newTitle).build();
        OwnerGroup savedOwnerGroup = ownerGroupMapService.save(ownerGroup2);
        //Total Should become 2
        assertEquals(2,ownerGroupMapService.findAll().size());
        //returned OwnerGroup should have same DEFAULT_TITLE and ID
        assertNotNull(savedOwnerGroup);
        assertNotNull(savedOwnerGroup.getId());
    }

    @Test
    void delete() {
        OwnerGroup existingOwnerGroup = ownerGroupMapService.findById(ownerGroupId);
        ownerGroupMapService.delete(existingOwnerGroup);
        assertEquals(0,ownerGroupMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerGroupMapService.deleteById(ownerGroupId);
        assertEquals(0,ownerGroupMapService.findAll().size());
    }

    @Test
    void findByPetOwnersTitle() {
        OwnerGroup foundOwnerGroup = ownerGroupMapService.findByPetOwnersTitle(DEFAULT_TITLE);
        assertNotNull(foundOwnerGroup);
        assertEquals(ownerGroupId,foundOwnerGroup.getId());
    }

    @Test
    void findAllByTitleLike() {
        List<OwnerGroup> foundOwnerGroups = ownerGroupMapService.findAllByTitleLike(DEFAULT_TITLE_PART);
        assertEquals(1,foundOwnerGroups.size());
        assertEquals(foundOwnerGroups.get(0).getTitle(), DEFAULT_TITLE);
        assertEquals(foundOwnerGroups.get(0).getId(), ownerGroupId);
    }
}