package com.erkanerturk.petclinic.web;

import com.erkanerturk.petclinic.model.Owner;
import com.erkanerturk.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PetClinicEditOwnerController {

    @Autowired
    PetClinicService petClinicService;

    @GetMapping("/owners/{id}")
    public String loadOwner(@PathVariable Long id, ModelMap modelMap) {
        Owner owner = petClinicService.findOwner(id);
        modelMap.put("owner", owner);
        return "editOwner";
    }

    @GetMapping("/owners/update/{id}")
    public String handleFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editOwner";
        }

        petClinicService.update(owner);
        redirectAttributes.addFlashAttribute("message", "Owner updated with id :" + owner.getId());
        return "redirect:/owners";
    }
}
