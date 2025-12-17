package tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO;

import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.StatusCommande;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeResponce {
    private Long idCommande;
    private LocalDate dateCommande;
    private StatusCommande statusCommande;
    private Long clientId; // avoid full client recursion
    private List<DetailCommandeRequest> detailsCommande;
}
