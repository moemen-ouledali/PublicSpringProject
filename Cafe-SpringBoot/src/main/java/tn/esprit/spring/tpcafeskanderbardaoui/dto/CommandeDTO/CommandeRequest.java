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
public class CommandeRequest {
    private LocalDate dateCommande;
    private Double totalCommande;
    private StatusCommande statusCommande;
    private List<DetailCommandeRequest> detailsCommande;

}
