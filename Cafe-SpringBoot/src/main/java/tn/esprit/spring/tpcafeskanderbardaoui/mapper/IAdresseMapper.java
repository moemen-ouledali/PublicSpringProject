package tn.esprit.spring.tpcafeskanderbardaoui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Adresse;

@Mapper(componentModel = "spring")
public interface IAdresseMapper {

    @Mapping(target = "ville", source = "ville")
    Adresse toEntity(AdresseRequest request);

    @Mapping(target = "ville", source = "ville")
    AdresseResponse toResponse(Adresse adresse);
}
