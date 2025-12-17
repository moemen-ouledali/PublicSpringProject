package tn.esprit.spring.tpcafeskanderbardaoui.services.Promotion;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionResponce;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionService {

    // =============================
    //        CRUD METHODS
    // =============================
    PromotionResponce addPromotion(PromotionRequest request);

    List<PromotionResponce> savePromotions(List<PromotionRequest> requests);

    PromotionResponce selectPromotionById(long id);

    List<PromotionResponce> selectAllPromotions();

    void deletePromotionById(long id);

    void deleteAllPromotions();

    long countingPromotions();

    boolean verifPromotionById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    // 1. Trouver les promotions par pourcentage exact
    List<PromotionResponce> findByPourcentageExact(Double pourcentage);

    // 2. Trouver les promotions par date de début
    List<PromotionResponce> findByDateDebut(LocalDate date);

    // 3. Trouver les promotions par date de fin
    List<PromotionResponce> findByDateFin(LocalDate date);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    boolean existsByPourcentage(Double pourcentage);

    // 5. Compter les promotions débutant après une date
    long countByDateDebutAfter(LocalDate date);

    // 6. Trouver les promotions actives à une date donnée
    List<PromotionResponce> findPromotionsActiveAt(LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    List<PromotionResponce> findByPourcentageAndDateDebutBetween(Double pourcentage, LocalDate startDate, LocalDate endDate);

    // 8. Trouver les promotions valides à une date spécifique
    List<PromotionResponce> findPromotionsValidAt(LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    List<PromotionResponce> findByPourcentagesInOrderByDateDebut(List<Double> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    List<PromotionResponce> findActivePromotionsOrderByPourcentage();

    // 11. Trouver les promotions sans date de fin
    List<PromotionResponce> findByDateFinIsNull();

    // 12. Trouver les promotions avec un pourcentage renseigné
    List<PromotionResponce> findByPourcentageIsNotNull();

    // 13. Trouver les promotions avec leurs articles associés
    List<PromotionResponce> findAllWithArticles();

    // 14. Trouver les promotions expirées
    List<PromotionResponce> findExpiredPromotions();


    void ajouterPromoEtAffecterAArticle(PromotionRequest request, long idArticle);

}