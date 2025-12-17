package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.CarteFidelite;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarteFideliteRepository extends JpaRepository<CarteFidelite, Long> {

    // 1. Trouver les cartes avec un nombre exact de points
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAcumules = :points")
    List<CarteFidelite> findByPointsExact(@Param("points") Integer points);

    // 2. Trouver les cartes créées à une date spécifique
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation = :date")
    List<CarteFidelite> findByDateCreation(@Param("date") LocalDate date);

    // 3. Compter les cartes avec plus de X points
    @Query("SELECT COUNT(cf) FROM CarteFidelite cf WHERE cf.pointsAcumules > :points")
    long countByPointsGreaterThan(@Param("points") Integer points);

    // 4. Supprimer les cartes créées avant une date
    @Modifying
    @Transactional
    @Query("DELETE FROM CarteFidelite cf WHERE cf.dateCreation < :date")
    int deleteByDateCreationBefore(@Param("date") LocalDate date);

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAcumules BETWEEN :minPoints AND :maxPoints AND cf.dateCreation > :date")
    List<CarteFidelite> findByPointsInRangeAndDateAfter(
            @Param("minPoints") Integer minPoints,
            @Param("maxPoints") Integer maxPoints,
            @Param("date") LocalDate date
    );

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAcumules >= :minPoints ORDER BY cf.dateCreation ASC")
    List<CarteFidelite> findByPointsGreaterThanEqualOrderByDateCreation(@Param("minPoints") Integer minPoints);

    // 7. Trouver les cartes créées entre deux dates
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation BETWEEN :startDate AND :endDate")
    List<CarteFidelite> findByDateCreationBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAcumules < :maxPoints OR cf.dateCreation < :date")
    List<CarteFidelite> findByLowPointsOrDateBefore(
            @Param("maxPoints") Integer maxPoints,
            @Param("date") LocalDate date
    );

    // 9. Trouver la carte avec le plus de points
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAcumules = (SELECT MAX(c.pointsAcumules) FROM CarteFidelite c)")
    Optional<CarteFidelite> findCardWithMaxPoints();

    // 10. Trouver les cartes sans date de création
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.dateCreation IS NULL")
    List<CarteFidelite> findByDateCreationIsNull();

    // 11. Trouver les cartes avec des points accumulés renseignés
    @Query("SELECT cf FROM CarteFidelite cf WHERE cf.pointsAcumules IS NOT NULL")
    List<CarteFidelite> findByPointsIsNotNull();

    // 12. Trouver les cartes avec leur client propriétaire (Par nom et prénom)
    @Query("SELECT cf FROM CarteFidelite cf JOIN cf.client c WHERE c.nom = :nom AND c.prenom = :prenom")
    List<CarteFidelite> findByClientNomAndPrenom(
            @Param("nom") String nom,
            @Param("prenom") String prenom
    );

    // 13. Trouver top 5 des cartes avec le plus de points
    @Query("SELECT cf FROM CarteFidelite cf ORDER BY cf.pointsAcumules DESC")
    List<CarteFidelite> findTop5ByOrderByPointsDesc();
}