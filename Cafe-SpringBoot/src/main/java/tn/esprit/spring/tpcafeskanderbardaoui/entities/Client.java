package tn.esprit.spring.tpcafeskanderbardaoui.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    // Adresse association
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    @JsonBackReference
    private Adresse adresse;

    // CarteFidelite association with cascade
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarteFidelite carteFidelite;

    // Commandes association
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commande> commandes;
}
