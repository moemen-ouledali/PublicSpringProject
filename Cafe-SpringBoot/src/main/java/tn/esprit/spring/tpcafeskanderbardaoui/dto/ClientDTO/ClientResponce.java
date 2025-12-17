package tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO;

import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteRequest;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponce {
    private Long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private CarteFideliteRequest carte;  // ✅ Use DTO instead of entity
}