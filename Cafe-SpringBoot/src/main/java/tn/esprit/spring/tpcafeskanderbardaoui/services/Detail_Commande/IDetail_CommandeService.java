package tn.esprit.spring.tpcafeskanderbardaoui.services.Detail_Commande;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeResponce;

import java.util.List;

public interface IDetail_CommandeService {

    // ================================
    //        CRUD METHODS
    // ================================
    DetailCommandeResponce addDetailCommande(DetailCommandeRequest request);

    List<DetailCommandeResponce> saveDetailsCommande(List<DetailCommandeRequest> requests);

    DetailCommandeResponce selectDetailCommandeById(long id);

    List<DetailCommandeResponce> selectAllDetailsCommande();

    void deleteDetailCommandeById(long id);

    void deleteAllDetailsCommande();

    long countingDetailsCommande();

    boolean verifDetailCommandeById(long id);

    // ================================
    //        JPQL QUERY METHODS
    // ================================

    // 1. Trouver les détails de commande par quantité exacte
    List<DetailCommandeResponce> findByQuantiteExact(Integer quantite);

    // 2. Trouver les détails par sous-total exact
    List<DetailCommandeResponce> findBySousTotalExact(Double sousTotal);

    // 3. Compter les détails avec plus de X quantités
    long countByQuantiteGreaterThan(Integer quantite);

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    boolean existsBySousTotalGreaterThan(Double montant);

    // 5. Trouver les détails avec une quantité dans une plage et un sous-total minimum
    List<DetailCommandeResponce> findByQuantiteBetweenAndSousTotalMin(Integer minQte, Integer maxQte, Double minSousTotal);

    // 6. Trouver les détails avec un sous-total dans une plage, triés par quantité
    List<DetailCommandeResponce> findBySousTotalBetweenOrderByQuantite(Double minSousTotal, Double maxSousTotal);

    // 7. Trouver les détails avec un sous-total après promotion dans une plage
    List<DetailCommandeResponce> findBySousTotalApresPromoBetween(Double min, Double max);

    // 8. Trouver les détails par quantité ou sous-total minimum
    List<DetailCommandeResponce> findByQuantiteOrSousTotalMin(Integer quantite, Double minSousTotal);

    // 9. Trouver les 5 détails les plus chers
    List<DetailCommandeResponce> findTop5BySousTotalDesc();

    // 10. Trouver les détails sans quantité renseignée
    List<DetailCommandeResponce> findByQuantiteIsNull();

    // 11. Trouver les détails avec un sous-total après promotion renseigné
    List<DetailCommandeResponce> findBySousTotalApresPromoIsNotNull();

    // 12. Trouver les détails avec leur commande et article
    List<DetailCommandeResponce> findAllWithCommandeAndArticle();
}
