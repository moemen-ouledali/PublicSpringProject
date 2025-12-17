package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // 1. Trouver les promotions par pourcentage exact
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo = :pourcentage")
    List<Promotion> findByPourcentageExact(@Param("pourcentage") Double pourcentage);

    // 2. Trouver les promotions par date de début
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo = :date")
    List<Promotion> findByDateDebut(@Param("date") LocalDate date);

    // 3. Trouver les promotions par date de fin
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo = :date")
    List<Promotion> findByDateFin(@Param("date") LocalDate date);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Promotion p WHERE p.pourcentagePromo = :pourcentage")
    boolean existsByPourcentage(@Param("pourcentage") Double pourcentage);

    // 5. Compter les promotions débutant après une date
    @Query("SELECT COUNT(p) FROM Promotion p WHERE p.dateDebutPromo > :date")
    long countByDateDebutAfter(@Param("date") LocalDate date);

    // 6. Trouver les promotions actives à une date donnée
    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.dateDebutPromo AND p.dateFinPromo")
    List<Promotion> findPromotionsActiveAt(@Param("date") LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo = :pourcentage AND p.dateDebutPromo BETWEEN :startDate AND :endDate")
    List<Promotion> findByPourcentageAndDateDebutBetween(
            @Param("pourcentage") Double pourcentage,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // 8. Trouver les promotions valides à une date spécifique
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= :date AND p.dateFinPromo >= :date")
    List<Promotion> findPromotionsValidAt(@Param("date") LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo IN :pourcentages ORDER BY p.dateDebutPromo ASC")
    List<Promotion> findByPourcentagesInOrderByDateDebut(@Param("pourcentages") List<Double> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    @Query("SELECT p FROM Promotion p WHERE CURRENT_DATE BETWEEN p.dateDebutPromo AND p.dateFinPromo ORDER BY p.pourcentagePromo DESC")
    List<Promotion> findActivePromotionsOrderByPourcentage();

    // 11. Trouver les promotions sans date de fin
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo IS NULL")
    List<Promotion> findByDateFinIsNull();

    // 12. Trouver les promotions avec un pourcentage renseigné
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo IS NOT NULL")
    List<Promotion> findByPourcentageIsNotNull();

    // 13. Trouver les promotions avec leurs articles associés
    @Query("SELECT DISTINCT p FROM Promotion p LEFT JOIN FETCH p.articles")
    List<Promotion> findAllWithArticles();

    // 14. Trouver les promotions expirées
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo < CURRENT_DATE")
    List<Promotion> findExpiredPromotions();
}