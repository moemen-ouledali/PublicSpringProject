package tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO;

import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCommandeResponce {
    private Long idDetailCommande;
    private Integer quantiteArticle;
    private Double sousTotalDetailArticle;
    private Double sousTotalDetailArticleApresPromo;

    private Long articleId; // instead of full Article object
    private Long commandeId;
}
