package tn.esprit.spring.tpcafeskanderbardaoui.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commande")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    private LocalDate dateCommande;

    private Double totalCommande;

    // ✅ Use enum instead of String
    @Enumerated(EnumType.STRING)
    private StatusCommande statusCommande;

    // Relation avec Client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Relation avec DetailCommande
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Detail_Commande> detailsCommande;
}
