package tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO;

import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.CarteFidelite;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private CarteFidelite carte;


}
