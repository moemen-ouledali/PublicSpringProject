package tn.esprit.spring.tpcafeskanderbardaoui.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;

import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    private String nomArticle;

    private Double prixArticle;

    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle; // Utilise l'enum CategorieProduit

    @OneToMany(mappedBy = "article")
    private List<Detail_Commande> detailsCommande;

    // Relation avec Promotion (many-to-many)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_promotion",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    @JsonIgnoreProperties("articles")  // Change this line
    private List<Promotion> promotions;

}
