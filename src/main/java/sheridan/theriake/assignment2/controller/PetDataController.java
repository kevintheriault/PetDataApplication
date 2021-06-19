package sheridan.theriake.assignment2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.stylesheets.LinkStyle;
import sheridan.theriake.assignment2.model.PetForm;
import sheridan.theriake.assignment2.service.PetDataService;

import java.util.List;

//Using LOMBOK for logging.
@Controller
@Slf4j
public class PetDataController {

//    Service injection.
    private final PetDataService petDataService;

    public PetDataController(PetDataService petDataService){
        this.petDataService = petDataService;
    }

    @GetMapping(value={"/", "/Index"})
    public String index(){
        log.trace("index.html served with index()");
        return "Index";
    }

    @GetMapping("/ListPets")
    public ModelAndView listPets(){
        log.trace("listPets() called");
        List<PetForm> listOfPets = petDataService.getAllPetForms();
        return new ModelAndView("ListPets", "pets", listOfPets);
    }

    @GetMapping("/AddPet")
    public ModelAndView addPet(){
        log.trace("addPet() called");
        ModelAndView modelAndView = new ModelAndView("AddPet", "petForm", new PetForm());
        return modelAndView;
    }

    @PostMapping("/ConfirmNewPet")
    public String confirmNewPet(
            @Validated @ModelAttribute("petForm") PetForm petForm,
            BindingResult bindingResult,
            Model model){
        log.trace("confirmNewPet() called");

//       Checking for errors
        if (bindingResult.hasErrors()) {
            log.trace("new petForm has errors");
            model.addAttribute("petForm", petForm);
            return "AddPet";
        } else {
            log.trace("Input validated: no errors");
            log.trace("Adding new pet form data to database")
            petDataService.insertPetForm(petForm);

            log.trace("Returning user to a new form ID for Post Redirect Get design prinicple");
            return "redirect:ConfirmNewPet/" + petForm.getId();
        }
    }

    @GetMapping

    @GetMapping("/DeletePet")
    public String deletePet(){
        log.trace("deletePet() called");
        return "DeletePet";
    }

    @GetMapping("/DetailPet")
    public String detailPet(){
        log.trace("detailPet() called");
        return "DetailPet";
    }

    @GetMapping("/EditPet")
    public String editPet(){
        log.trace("editPet() called");

        return "EditPet";
    }


}
