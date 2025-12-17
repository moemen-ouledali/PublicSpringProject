package tn.esprit.spring.tpcafeskanderbardaoui.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "carte_fidelite")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarteFidelite;

    private Integer pointsAcumules;

    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
