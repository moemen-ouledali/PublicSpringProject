package tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO;

import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCommandeRequest {
    private Long idDetailCommande;
    private Integer quantiteArticle;
    private Double sousTotalDetailArticle;
    private Double sousTotalDetailArticleApresPromo;
    private Article article;
    private Commande commande;


}
