package bjorn.petprojects.bpppetbook.bootstrap;

import bjorn.petprojects.bpppetbook.model.Owner;
import bjorn.petprojects.bpppetbook.model.OwnerGroup;
import bjorn.petprojects.bpppetbook.model.Pet;
import bjorn.petprojects.bpppetbook.model.PetType;
import bjorn.petprojects.bpppetbook.services.OwnerGroupService;
import bjorn.petprojects.bpppetbook.services.OwnerService;
import bjorn.petprojects.bpppetbook.services.PetService;
import bjorn.petprojects.bpppetbook.services.PetTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DummyDataLoader implements CommandLineRunner {

    private final OwnerGroupService ownerGroupService;
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public DummyDataLoader(OwnerGroupService ownerGroupService, OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerGroupService = ownerGroupService;
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        int existingCount = petTypeService.findAll().size();
        if(existingCount == 0){
            loadData();
        }
    }

    private void loadData(){
        // Creating PetTypes
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedDogType = petTypeService.save(dog);
        PetType savedCatType = petTypeService.save(cat);

        // Creating owners
        Owner ownerBjorn = Owner.builder().firstName("Bjorn").lastName("De Craemer").birthDate(new Date(1978,02,07)).build();
        Owner ownerKristel = Owner.builder().firstName("Kristel").lastName("Van Mileghem").birthDate(new Date(1978,10,07)).build();
        Owner ownerJohnDoe = Owner.builder().firstName("John").lastName("Doe").birthDate(new Date(1923,05,01)).build();
        Owner ownerAlanTuring = Owner.builder().firstName("Alan").lastName("Turing").birthDate(new Date(1912,06,23)).build();

        Owner savedOwnerBjorn = ownerService.save(ownerBjorn);
        Owner savedOwnerKristel = ownerService.save(ownerKristel);
        Owner savedOwnerJohnDoe = ownerService.save(ownerJohnDoe);
        Owner savedOwnerAlanTuring = ownerService.save(ownerAlanTuring);

        // Creating Pets
        Pet puffyPet = Pet.builder().petType(savedCatType).name("Fuffy").birthDate(new Date(2018,06,10)).build();
        Pet jackyPet = Pet.builder().petType(savedCatType).name("Jack").birthDate(new Date(2015,05,04)).build();
        Pet chihiroPet = Pet.builder().petType(savedCatType).name("Chihiro").birthDate(new Date(2016,03,26)).build();
        Pet ipsumPet = Pet.builder().petType(savedDogType).name("Ipsum the mangy dog").birthDate(new Date(2021,01,8)).build();
        puffyPet = petService.save(puffyPet);
        jackyPet = petService.save(jackyPet);
        chihiroPet = petService.save(chihiroPet);
        ipsumPet = petService.save(ipsumPet);


        // Creating owner Groups
        Set<Owner> owners1 = new HashSet<>();
        owners1.add(savedOwnerBjorn);
        owners1.add(savedOwnerKristel);

        Set<Owner> owners2 = new HashSet<>();
        owners2.add(savedOwnerAlanTuring);
        owners2.add(savedOwnerJohnDoe);

        Set<Pet> pets1 = new HashSet<>();
        pets1.add(puffyPet);
        pets1.add(chihiroPet);
        pets1.add(jackyPet);

        Set<Pet> pets2 = new HashSet<>();
        pets2.add(ipsumPet);

        OwnerGroup ownerGroup1 = OwnerGroup.builder().title("Schorsse's Feline Retreat").owners(owners1).pets(pets1).build();
        ownerGroupService.save(ownerGroup1);
        OwnerGroup ownerGroup2 = OwnerGroup.builder().title("History's dog lovers").owners(owners2).pets(pets2).build();
        ownerGroupService.save(ownerGroup2);
        System.out.println("Loaded dummy data!");
    }
}
