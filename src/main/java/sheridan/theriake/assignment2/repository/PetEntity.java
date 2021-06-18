package sheridan.theriake.assignment2.repository;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "pet")
@Data
public class PetEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_name")
    private String petName = "";

    @Column(name = "owner_last_name")
    private String ownerLastName = "";

    @Column(name = "pet_kind")
    private String petKind = "";

    @Column(name = "pet_gender")
    private String petGender = "";

    @Column(name = "vaccinated")
    private Boolean vaccinated = false;

}
