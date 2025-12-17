package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Promotion;

@Mapper(componentModel = "spring")
public interface IArticleMapper {

    ArticleResponse toResponse(Article article);

    @Mapping(target = "idArticle", ignore = true)
    @Mapping(target = "promotions", ignore = true)  // ✅ Ignore - we handle it manually
    Article toEntity(ArticleRequest request);

    PromotionResponce toPromotionResponse(Promotion promotion);  // ✅ Make sure this exists
}