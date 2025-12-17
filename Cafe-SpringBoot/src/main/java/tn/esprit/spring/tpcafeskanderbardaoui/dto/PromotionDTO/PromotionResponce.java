package tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionResponce {
    private Long idPromotion;
    private Double pourcentagePromo;
    private LocalDate dateDebutPromo;
    private LocalDate dateFinPromo;
}
