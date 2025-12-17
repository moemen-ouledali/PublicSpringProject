package tn.esprit.spring.tpcafeskanderbardaoui.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromotion;

    private Double pourcentagePromo;

    private LocalDate dateDebutPromo;

    private LocalDate dateFinPromo;

    // Relation ManyToMany avec Article
    @ManyToMany(mappedBy = "promotions")
    @JsonIgnore  // Change this line - completely ignore during serialization
    private List<Article> articles;

}
