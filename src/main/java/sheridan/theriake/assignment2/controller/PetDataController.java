package sheridan.theriake.assignment2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
            log.trace("Adding new pet form data to database");
            petDataService.insertPetForm(petForm);

            log.trace("Returning user to a new form ID for Post Redirect Get design prinicple");
            return "redirect:ConfirmNewPet/" + petForm.getId();
        }
    }

    @GetMapping("/ConfirmNewPet/{id}")
    public String confirmNewPet(@PathVariable(name="id") int id, Model model){
        log.trace("get mapped confirmNewPet() called");
        PetForm petForm = petDataService.getPetForm(id);
        model.addAttribute("petForm", petForm);

        return "ConfirmNewPet";
    }

    @GetMapping("/DeletePet")
    public String deletePet(@RequestParam int id, Model model){
        log.trace("deletePet() called");
        PetForm petForm = petDataService.getPetForm(id);
        model.addAttribute("petForm", petForm);
        return "DeletePet";
    }

//    Post mapping for when user hits 'confirm delete'.  This just deletes using Post data and redirects to list.
    @PostMapping("/DeleteEntry")
    public String deletePet(@RequestParam int id) {
        log.trace("get mapped deletePet() is called");
            petDataService.deletePetForm(id);
        return "redirect:ListPets";
    }

    @GetMapping("/DetailPet/{id}")
    public String detailPet(@PathVariable int id, Model model){
        log.trace("detailPet() called");
        PetForm petForm = petDataService.getPetForm(id);
        model.addAttribute(petForm);
        return "DetailPet";
    }

    @GetMapping("/EditPet")
    public String editPet(@RequestParam int id, Model model){
        log.trace("editPet() called");
        PetForm petForm = petDataService.getPetForm(id);
        model.addAttribute("petForm", petForm);
        return "EditPet";
    }

    @PostMapping("/UpdateData")
    public String updateEntry(
            @Validated @ModelAttribute("petForm") PetForm petForm,
            BindingResult bindingResult,
            Model model) {
        log.trace("updateEntry() called");
        if (bindingResult.hasErrors()) {
            log.trace("Input errors");
            model.addAttribute("petForm", petForm);
            return "EditPet";
        } else {
            log.trace("Input validated: no errors");
            petDataService.updatePetForm(petForm);
            log.debug("id = " + petForm.getId());
            return "redirect:DetailPet/" + petForm.getId();
        }
    }
}
