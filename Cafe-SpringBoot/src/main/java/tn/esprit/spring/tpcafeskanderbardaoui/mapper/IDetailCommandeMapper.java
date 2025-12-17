package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Detail_Commande;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDetailCommandeMapper {

    // Map DetailCommandeRequest DTO to Detail_Commande entity
    @Mapping(target = "quantiteArticle", source = "quantiteArticle")
    @Mapping(target = "sousTotalDetailArticle", source = "sousTotalDetailArticle")
    @Mapping(target = "sousTotalDetailArticleApresPromo", source = "sousTotalDetailArticleApresPromo")
    @Mapping(target = "article", source = "article") // map full Article object
    @Mapping(target = "commande", source = "commande") // map full Commande object
    Detail_Commande toEntity(DetailCommandeRequest request);

    // Map Detail_Commande entity to DetailCommandeResponce DTO
    @Mapping(source = "article.idArticle", target = "articleId") // map article ID
    @Mapping(source = "commande.idCommande", target = "commandeId") // map commande ID
    @Mapping(target = "idDetailCommande", source = "idDetailCommande")
    @Mapping(target = "quantiteArticle", source = "quantiteArticle")
    @Mapping(target = "sousTotalDetailArticle", source = "sousTotalDetailArticle")
    @Mapping(target = "sousTotalDetailArticleApresPromo", source = "sousTotalDetailArticleApresPromo")
    DetailCommandeResponce toResponse(Detail_Commande detailCommande);

    // Map list of entities to list of DTOs
    List<DetailCommandeResponce> toResponseList(List<Detail_Commande> details);

    // Map list of DTOs to list of entities
    List<Detail_Commande> toEntityList(List<DetailCommandeRequest> requests);
}
