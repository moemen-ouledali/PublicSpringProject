package tn.esprit.spring.tpcafeskanderbardaoui.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_commande")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detail_Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailCommande;

    private Integer quantiteArticle;

    private Double sousTotalDetailArticle;

    private Double sousTotalDetailArticleApresPromo;

    // Relation vers Commande
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    // Relation vers Article
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
