package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Detail_Commande;

import java.util.List;

public interface DetailCommandeRepository extends JpaRepository<Detail_Commande, Long> {

    // 1. Trouver les détails de commande par quantité exacte
    List<Detail_Commande> findByQuantiteArticle(Integer quantite);

    // 2. Trouver les détails par sous-total exact
    List<Detail_Commande> findBySousTotalDetailArticle(Double sousTotal);

    // 3. Compter les détails avec plus de X quantités
    long countByQuantiteArticleGreaterThan(Integer quantite);

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    boolean existsBySousTotalDetailArticleGreaterThan(Double montant);

    // 5. Trouver les détails avec une quantité dans une plage et un sous-total minimum
    @Query("SELECT d FROM Detail_Commande d WHERE d.quantiteArticle BETWEEN :minQte AND :maxQte AND d.sousTotalDetailArticle >= :minSousTotal")
    List<Detail_Commande> findByQuantiteBetweenAndSousTotalMin(
            @Param("minQte") Integer minQte,
            @Param("maxQte") Integer maxQte,
            @Param("minSousTotal") Double minSousTotal
    );

    // 6. Trouver les détails avec un sous-total dans une plage, triés par quantité
    @Query("SELECT d FROM Detail_Commande d WHERE d.sousTotalDetailArticle BETWEEN :minSousTotal AND :maxSousTotal ORDER BY d.quantiteArticle ASC")
    List<Detail_Commande> findBySousTotalBetweenOrderByQuantite(
            @Param("minSousTotal") Double minSousTotal,
            @Param("maxSousTotal") Double maxSousTotal
    );

    // 7. Trouver les détails avec un sous-total après promotion dans une plage
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoBetween(Double min, Double max);

    // 8. Trouver les détails par quantité ou sous-total minimum
    @Query("SELECT d FROM Detail_Commande d WHERE d.quantiteArticle = :quantite OR d.sousTotalDetailArticle >= :minSousTotal")
    List<Detail_Commande> findByQuantiteOrSousTotalMin(
            @Param("quantite") Integer quantite,
            @Param("minSousTotal") Double minSousTotal
    );

    // 9. Trouver les 5 détails les plus chers
    @Query("SELECT d FROM Detail_Commande d ORDER BY d.sousTotalDetailArticle DESC")
    List<Detail_Commande> findTop5ByOrderBySousTotalDetailArticleDesc();

    // 10. Trouver les détails sans quantité renseignée
    List<Detail_Commande> findByQuantiteArticleIsNull();

    // 11. Trouver les détails avec un sous-total après promotion renseigné
    List<Detail_Commande> findBySousTotalDetailArticleApresPromoIsNotNull();

    // 12. Trouver les détails avec leur commande et article (fetch join)
    @Query("SELECT d FROM Detail_Commande d LEFT JOIN FETCH d.commande LEFT JOIN FETCH d.article")
    List<Detail_Commande> findAllWithCommandeAndArticle();
}
