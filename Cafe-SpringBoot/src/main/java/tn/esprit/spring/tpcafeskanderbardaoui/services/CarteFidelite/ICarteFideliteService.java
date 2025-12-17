package tn.esprit.spring.tpcafeskanderbardaoui.services.CarteFidelite;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteResponce;

import java.time.LocalDate;
import java.util.List;

public interface ICarteFideliteService {

    // =============================
    //        CRUD METHODS
    // =============================
    CarteFideliteResponce addCarteFidelite(CarteFideliteRequest request);

    List<CarteFideliteResponce> saveCarteFidelites(List<CarteFideliteRequest> requests);

    CarteFideliteResponce selectCarteFideliteById(long id);

    List<CarteFideliteResponce> selectAllCarteFidelites();

    void deleteCarteFideliteById(long id);

    void deleteAllCarteFidelites();

    long countingCarteFidelites();

    boolean verifCarteFideliteById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    // 1. Trouver les cartes avec un nombre exact de points
    List<CarteFideliteResponce> findByPointsExact(Integer points);

    // 2. Trouver les cartes créées à une date spécifique
    List<CarteFideliteResponce> findByDateCreation(LocalDate date);

    // 3. Compter les cartes avec plus de X points
    long countByPointsGreaterThan(Integer points);

    // 4. Supprimer les cartes créées avant une date
    int deleteByDateCreationBefore(LocalDate date);

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    List<CarteFideliteResponce> findByPointsInRangeAndDateAfter(Integer minPoints, Integer maxPoints, LocalDate date);

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    List<CarteFideliteResponce> findByPointsGreaterThanEqualOrderByDateCreation(Integer minPoints);

    // 7. Trouver les cartes créées entre deux dates
    List<CarteFideliteResponce> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    List<CarteFideliteResponce> findByLowPointsOrDateBefore(Integer maxPoints, LocalDate date);

    // 9. Trouver la carte avec le plus de points
    CarteFideliteResponce findCardWithMaxPoints();

    // 10. Trouver les cartes sans date de création
    List<CarteFideliteResponce> findByDateCreationIsNull();

    // 11. Trouver les cartes avec des points accumulés renseignés
    List<CarteFideliteResponce> findByPointsIsNotNull();

    // 12. Trouver les cartes avec leur client propriétaire (Par nom et prénom)
    List<CarteFideliteResponce> findByClientNomAndPrenom(String nom, String prenom);

    // 13. Trouver top 5 des cartes avec le plus de points
    List<CarteFideliteResponce> findTop5ByOrderByPointsDesc();
}