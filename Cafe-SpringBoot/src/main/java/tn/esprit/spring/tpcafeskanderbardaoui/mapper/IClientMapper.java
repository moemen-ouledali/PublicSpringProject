package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.CarteFidelite;

@Mapper(componentModel = "spring")
public interface IClientMapper {


    @Mapping(source = "carte.pointsAcumules", target = "carteFidelite.pointsAcumules") // ✅ map client.card → entity.card
    @Mapping(source = "dateNaissance", target = "dateNaissance")   // ✅ added
    Client toEntity(ClientRequest request);

    @Mapping(source = "carteFidelite.pointsAcumules", target = "carte.pointsAcumules") // ✅ map entity.card → DTO.card
    @Mapping(source = "carteFidelite.dateCreation", target = "carte.dateCreation")
    @Mapping(source = "dateNaissance", target = "dateNaissance")
    ClientResponce toResponse(Client client);
}
