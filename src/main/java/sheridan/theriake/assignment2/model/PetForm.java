package sheridan.theriake.assignment2.model;

import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;

// Using LOMBOK @Data to create constructors/getters/setters
@Data
public class PetForm implements Serializable {

    @NotBlank
    @Pattern(regexp= "[A-Za-z]*")
    private String petName ="";

    @NotBlank
    @Pattern(regexp= "[A-Za-z]*")
    private String ownerLastName ="";

    @NotBlank
    @Pattern(regexp= "(Cat|Dog|Rabbit)?")
    private String petKind ="";

    @NotBlank
    @Pattern(regexp= "(Male|Female)?")
    private String petGender ="";

//    NOTE: LOMBOK sometimes makes boolean getter/setters for boolean as 'get' not 'is'.
    private boolean vaccinated = false;
    private int id =0;

}
