package sheridan.theriake.assignment2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sheridan.theriake.assignment2.service.PetDataService;

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
    public String listPets(){
        log.trace("listPets() called");
        return "ListPets";
    }

    @GetMapping("/AddPet")
    public String addPet(){
        log.trace("addPet() called");
        return "AddPet";
    }

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
