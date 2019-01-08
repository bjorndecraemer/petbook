package bjorn.petprojects.bpppetbook.controllers;

import bjorn.petprojects.bpppetbook.model.PetType;
import bjorn.petprojects.bpppetbook.services.OwnerGroupService;
import bjorn.petprojects.bpppetbook.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class OwnerGroupListController {
    private final OwnerGroupService ownerGroupService;
    private final PetTypeService petTypeService;

    public OwnerGroupListController(OwnerGroupService ownerGroupService, PetTypeService petTypeService) {
        this.ownerGroupService = ownerGroupService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("pettypes")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @RequestMapping("/ownergroups")
    public String getOwnerGroupList(Model model){
        model.addAttribute("ownergroups", ownerGroupService.findAll());
        return "ownergroups/index";
    }
}
