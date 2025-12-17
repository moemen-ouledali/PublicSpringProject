package tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseResponse {
    private Long idAdresse;
    private String rue;
    private String ville;
}
