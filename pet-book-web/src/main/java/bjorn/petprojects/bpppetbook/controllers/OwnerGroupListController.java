package bjorn.petprojects.bpppetbook.controllers;

import bjorn.petprojects.bpppetbook.controllers.helpers.OwnerGroupSearchCriteria;
import bjorn.petprojects.bpppetbook.model.OwnerGroup;
import bjorn.petprojects.bpppetbook.model.PetType;
import bjorn.petprojects.bpppetbook.services.OwnerGroupService;
import bjorn.petprojects.bpppetbook.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class OwnerGroupListController {
    private final OwnerGroupService ownerGroupService;
    private final PetTypeService petTypeService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

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
        model.addAttribute("searchCriteria", new OwnerGroupSearchCriteria());
        model.addAttribute("ownergroups", ownerGroupService.findAll());
        return "ownergroups/index";
    }
    @GetMapping("/ownergroups/search")
    public String processFindForm(@ModelAttribute("searchCriteria") OwnerGroupSearchCriteria searchCriteria, BindingResult result, Model model){
        List<OwnerGroup> results;

        if(searchCriteria == null || searchCriteria.getSearchCriteria() == null || searchCriteria.getSearchCriteria().isEmpty()){
            return "redirect:/ownergroups";
        }
        else{
            results = ownerGroupService.findAllByTitleLike(searchCriteria.getSearchCriteria());
            if(results.isEmpty()){
                result.rejectValue("searchCriteria", "notFound", "not found");
            }
        }
        model.addAttribute("ownergroups", results);
        return "ownergroups/index";

    }
}
