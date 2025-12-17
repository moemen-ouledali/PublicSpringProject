package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Promotion;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IPromotionMapper {

    @Mapping(target = "articles", source = "articleIds", qualifiedByName = "mapIdsToArticles")
    Promotion toEntity(PromotionRequest request);

    PromotionResponce toResponse(Promotion promotion);

    List<PromotionResponce> toResponseList(List<Promotion> promotions);

    @Named("mapArticlesToIds")
    default List<Long> mapArticlesToIds(List<Article> articles) {
        if (articles == null) return null;
        return articles.stream().map(Article::getIdArticle).collect(Collectors.toList());
    }

    @Named("mapIdsToArticles")
    default List<Article> mapIdsToArticles(List<Long> ids) {
        // ONLY create Article references with ID; Hibernate must manage them
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Article a = new Article();
            a.setIdArticle(id);
            return a;
        }).collect(Collectors.toList());
    }
}
