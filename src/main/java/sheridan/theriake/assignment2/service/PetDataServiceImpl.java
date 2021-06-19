package sheridan.theriake.assignment2.service;

import org.springframework.stereotype.Service;
import sheridan.theriake.assignment2.model.PetForm;
import sheridan.theriake.assignment2.repository.PetEntity;
import sheridan.theriake.assignment2.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetDataServiceImpl implements PetDataService {

    private final PetRepository petRepo;

    PetDataServiceImpl(PetRepository petRepo) {
        this.petRepo = petRepo;
    }

    private static void copyFormToEntity(PetForm petForm, PetEntity pet) {
        pet.setId(pet.getId());
        pet.setPetName(petForm.getPetName());
        pet.setOwnerLastName(petForm.getOwnerLastName());
        pet.setPetKind(petForm.getPetKind());
        pet.setPetGender(petForm.getPetGender());
        pet.setVaccinated(petForm.isVaccinated());
    }

    private static void copyEntityToForm(PetForm petForm, PetEntity pet) {
        petForm.setId(pet.getId());
        petForm.setPetName(pet.getPetName());
        petForm.setOwnerLastName(pet.getOwnerLastName());
        petForm.setPetKind(pet.getPetKind());
        petForm.setPetGender(pet.getPetGender());

//        LOMBOK CREATES "GET" BOOLEAN GETTER RATHER THAN "IS"
        petForm.setVaccinated(pet.getVaccinated());
    }

    public void insertPetForm(PetForm petForm) {
        PetEntity pet = new PetEntity();
        copyFormToEntity(petForm, pet);
        pet = petRepo.save(pet);
        petForm.setId(pet.getId());
    }

    public List<PetForm> getAllPetForms() {
        List<PetForm> formList = new ArrayList<>();
        List<PetEntity> petList = petRepo.findAll();
        for (PetEntity pet : petList) {
            PetForm petForm = new PetForm();
            copyEntityToForm(petForm, pet);
            formList.add(petForm);
        }
        return formList;
    }

    public void deleteAllPetForms() {
        petRepo.deleteAll();
    }

    public void deletePetForm(int id) {
        petRepo.deleteById(id);
    }

    public PetForm getPetForm(int id){
        Optional<PetEntity> result = petRepo.findById(id);
        if(result.isPresent()){
            PetForm petForm = new PetForm();
            PetEntity pet = result.get();
            copyEntityToForm(petForm, pet);
            return petForm;
        }
        return null;
    }

    public void updatePetForm(PetForm form){
        Optional<PetEntity> result = petRepo.findById(form.getId());
        if(result.isPresent()){
            PetEntity pet = result.get();
            copyFormToEntity(form, pet);
            petRepo.save(pet);
        }
    }
}
