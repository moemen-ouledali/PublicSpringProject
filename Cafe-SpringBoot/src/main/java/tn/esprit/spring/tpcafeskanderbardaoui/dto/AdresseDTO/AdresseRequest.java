package tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseRequest {
    private String rue;
    private String ville;

}
