package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.CarteFidelite;

@Mapper(componentModel = "spring")
public interface ICarteFideliteMapper {

    // Map Request DTO to Entity
    CarteFidelite toEntity(CarteFideliteRequest request);

    // Map Entity to Response DTO
    CarteFideliteResponce toResponse(CarteFidelite carteFidelite);
}


