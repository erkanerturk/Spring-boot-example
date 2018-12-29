package com.erkanerturk.petclinic.web;

import com.erkanerturk.petclinic.model.Owner;
import com.erkanerturk.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PetClinicNewOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    @GetMapping(value = "/owners/new")
    public String newOwner() {
        return "newOwner";
    }

    @ModelAttribute
    public Owner initModel() {
        return new Owner();
    }

    @PostMapping(value = "/owners/new")
    public String handleFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "newOwner";
        }

        petClinicService.addOwner(owner);
        redirectAttributes.addFlashAttribute("message", "Owner created with id :" + owner.getId());
        return "redirect:/owners";
    }
}
