package sheridan.theriake.assignment2.service;

import sheridan.theriake.assignment2.model.PetForm;
import java.util.List;

//Interface provides all required methods for creating/editing/deleting/showing pet form data.
public interface PetDataService {
    void insertPetForm(PetForm form);
    List<PetForm> getAllPetForms();
    void deleteAllPetForms();
    void deletePetForm(int id);
    PetForm getPetForm(int id);
    void updatePetForm(PetForm form);
}
