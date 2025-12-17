package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.IterableMapping;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Detail_Commande;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICommandeMapper {

    // Map CommandeRequest to Commande entity
    @Mapping(target = "client", ignore = true) // set manually in service
    @Mapping(target = "detailsCommande", source = "detailsCommande") // map list from DTO to entity
    Commande toEntity(CommandeRequest request);

    // Map Commande entity to CommandeResponce DTO
    @Mapping(source = "client.idClient", target = "clientId") // corrected
    @Mapping(source = "detailsCommande", target = "detailsCommande") // map entity list to response DTO list
    CommandeResponce toResponse(Commande commande);

    // Map DetailCommandeRequest to DetailCommande entity
    Detail_Commande toDetailEntity(DetailCommandeRequest request);

    // Map DetailCommande entity to DetailCommandeResponce DTO
    @Mapping(source = "article.idArticle", target = "articleId") // map article ID
    DetailCommandeResponce toDetailResponse(Detail_Commande detailCommande);

    // Map lists
    @IterableMapping(elementTargetType = Detail_Commande.class)
    List<Detail_Commande> toDetailEntities(List<DetailCommandeRequest> requests);

    @IterableMapping(elementTargetType = DetailCommandeResponce.class)
    List<DetailCommandeResponce> toDetailResponses(List<Detail_Commande> details);
}
