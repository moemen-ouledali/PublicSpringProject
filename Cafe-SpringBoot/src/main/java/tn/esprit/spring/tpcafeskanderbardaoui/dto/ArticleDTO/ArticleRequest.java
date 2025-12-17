package tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO;

import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {
    private String nomArticle;
    private Double prixArticle;
    private TypeArticle typeArticle;
    private List<Long> promotionIds;

}
