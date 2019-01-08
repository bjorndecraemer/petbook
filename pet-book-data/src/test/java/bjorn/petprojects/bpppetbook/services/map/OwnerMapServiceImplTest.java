package bjorn.petprojects.bpppetbook.services.map;

import bjorn.petprojects.bpppetbook.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceImplTest {

    private OwnerMapServiceImpl ownerMapService;

    private final Long ownerId1 = 1L;
    private final String firstName1 = "John";
    private final String firstName1ExistingPart = "oh";
    private final String lastName1 = "Doe";
    private final String lastName1ExistingPart = "oe";
    private final Date birthDate1 = new Date();
    private final Long ownerId2 = 2L;
    private final String firstName2 = "Alan";
    private final String lastName2 = "Turingoo";
    private final Date birthDate2 = new Date();
    private final Long nonExistingOwnerId1 = 1000L;
    private final String nonExistingNamePart = "AAA";


    @BeforeEach
    void setUp() {
        Owner owner1 = Owner.builder().id(ownerId1).firstName(firstName1).lastName(lastName1).birthDate(birthDate1).build();
        Owner owner2 = Owner.builder().id(ownerId2).firstName(firstName2).lastName(lastName2).birthDate(birthDate2).build();
        ownerMapService = new OwnerMapServiceImpl();
        ownerMapService.save(owner1);
        ownerMapService.save(owner2);
    }

    @Test
    void findAll() {
        assertEquals(2,ownerMapService.findAll().size());
    }

    @Test
    void findById_Existing_Id() {
        Owner foundOwner = ownerMapService.findById(ownerId1);
        assertEquals(ownerId1,foundOwner.getId());
        assertEquals(firstName1,foundOwner.getFirstName());
        assertEquals(lastName1,foundOwner.getLastName());
        assertEquals(birthDate1,foundOwner.getBirthDate());
    }

    @Test
    void findById_Non_Existing_Id() {
        Owner foundOwner = ownerMapService.findById(nonExistingOwnerId1);
        assertNull(foundOwner);
    }

    @Test
    void save_Existing_Id() {
        Owner overWritingOwner = Owner.builder().id(ownerId1).firstName("NEW_NAME").build();
        ownerMapService.save(overWritingOwner);
        Owner foundOwner = ownerMapService.findById(ownerId1);
        assertEquals("NEW_NAME",foundOwner.getFirstName());
    }

    @Test
    void save_No_Id() {
        Owner newOwner = Owner.builder().firstName("NEW_NAME").build();
        Owner savedOwner = ownerMapService.save(newOwner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertEquals(3, ownerMapService.findAll().size());
        assertEquals("NEW_NAME",savedOwner.getFirstName());
    }

    @Test
    void delete() {
        Owner loadedOwner = ownerMapService.findById(ownerId1);
        ownerMapService.delete(loadedOwner);
        assertEquals(1,ownerMapService.findAll().size());
        Owner foundOwner = ownerMapService.findById(ownerId1);
        assertNull(foundOwner);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId1);
        assertEquals(1,ownerMapService.findAll().size());
        Owner foundOwner = ownerMapService.findById(ownerId1);
        assertNull(foundOwner);
    }

    @Test
    void findByLastName_Existing_LastName() {
        List<Owner> foundOwners = ownerMapService.findByLastName(lastName1);
        assertEquals(1,foundOwners.size());
        assertEquals(ownerId1,foundOwners.get(0).getId());
    }
    @Test
    void findByLastName_Non_Existing_LastName() {
        List<Owner> foundOwners = ownerMapService.findByLastName(nonExistingNamePart);
        assertEquals(0,foundOwners.size());
    }
    @Test
    void findByFirstName_Existing() {
        List<Owner> foundOwner = ownerMapService.findByFirstName(firstName1);
        assertEquals(1,foundOwner.size());
        assertEquals(ownerId1,foundOwner.get(0).getId());
    }
    @Test
    void findByFirstName_Non_Existing() {
        List<Owner> foundOwners = ownerMapService.findByFirstName(nonExistingNamePart);
        assertEquals(0,foundOwners.size());
    }

    @Test
    void findAllByFirstNameLike_Existing_1() {
        List<Owner> foundOwners = ownerMapService.findAllByFirstNameLike(firstName1ExistingPart);
        assertEquals(1,foundOwners.size());
        assertEquals(ownerId1,foundOwners.get(0).getId());
    }

    @Test
    void findAllByFirstNameLike_Existing_More() {
        List<Owner> foundOwners = ownerMapService.findAllByLastNameLike("o");
        assertEquals(2,foundOwners.size());
    }
    @Test
    void findAllByFirstNameLike_Existing_None() {
        List<Owner> foundOwners = ownerMapService.findAllByLastNameLike(nonExistingNamePart);
        assertEquals(0,foundOwners.size());
    }


    @Test
    void findAllByLastNameLike_Existing_1() {
        List<Owner> foundOwners = ownerMapService.findAllByLastNameLike(lastName1ExistingPart);
        assertEquals(1,foundOwners.size());
        assertEquals(ownerId1,foundOwners.get(0).getId());
    }

    @Test
    void findAllByLastNameLike_Existing_More() {
        List<Owner> foundOwners = ownerMapService.findAllByLastNameLike("o");
        assertEquals(2,foundOwners.size());
    }
    @Test
    void findAllByLastNameLike_Existing_None() {
        List<Owner> foundOwners = ownerMapService.findAllByLastNameLike(nonExistingNamePart);
        assertEquals(0,foundOwners.size());
    }

}