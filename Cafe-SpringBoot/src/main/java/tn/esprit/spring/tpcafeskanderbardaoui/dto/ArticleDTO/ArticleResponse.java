package tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionResponce;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {
    private Long idArticle;
    private String nomArticle;
    private Double prixArticle;
    private TypeArticle typeArticle;

    private List<PromotionResponce> promotions;  // ✅ Add this field


}
