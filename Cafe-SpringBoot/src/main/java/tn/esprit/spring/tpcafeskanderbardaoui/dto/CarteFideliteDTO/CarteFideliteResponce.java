package tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFideliteResponce {
    private Long idCarteFidelite;
    private Integer pointsAcumules;
    private LocalDate dateCreation;
}
