package tn.esprit.spring.tpcafeskanderbardaoui.services.Commande;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.StatusCommande;

import java.time.LocalDate;
import java.util.List;

public interface ICommandeService {

    // =============================
    //        CRUD METHODS
    // =============================
    CommandeResponce addCommande(CommandeRequest request);

    List<CommandeResponce> saveCommandes(List<CommandeRequest> requests);

    CommandeResponce selectCommandeById(long id);

    List<CommandeResponce> selectAllCommandes();

    void deleteCommandeById(long id);

    void deleteAllCommandes();

    long countingCommandes();

    boolean verifCommandeById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    // 1. Trouver les commandes par statut
    List<CommandeResponce> findByStatut(StatusCommande statut);

    // 2. Trouver les commandes par date exacte
    List<CommandeResponce> findByDateExacte(LocalDate date);

    // 3. Compter les commandes par statut
    long countByStatut(StatusCommande statut);

    // 4. Supprimer les commandes antérieures à une date
    int deleteByDateBefore(LocalDate date);

    // 5. Trouver les commandes entre deux dates avec un statut spécifique
    List<CommandeResponce> findByDateBetweenAndStatut(LocalDate startDate, LocalDate endDate, StatusCommande statut);

    // 6. Trouver les commandes avec un total supérieur à un montant et un statut différent
    List<CommandeResponce> findByTotalGreaterThanAndStatutNot(Double montant, StatusCommande statut);

    // 7. Trouver les commandes avec certains statuts, triées par date
    List<CommandeResponce> findByStatutsInOrderByDate(List<StatusCommande> statuts);

    // 8. Trouver les commandes avant une date avec un total dans une plage
    List<CommandeResponce> findByDateBeforeAndTotalBetween(LocalDate date, Double minTotal, Double maxTotal);

    // 9. Trouver les commandes où le statut se termine par une lettre spécifique
    List<CommandeResponce> findByStatutEndingWith(String suffix);

    // 10. Trouver les commandes sans statut renseigné
    List<CommandeResponce> findByStatutIsNull();

    // 11. Trouver les commandes avec un total renseigné
    List<CommandeResponce> findByTotalIsNotNull();

    // 12. Trouver les commandes avec leurs détails et client
    List<CommandeResponce> findAllWithDetailsAndClient();

    // 13. Trouver le top 3 des commandes les plus récentes
    List<CommandeResponce> findTop3RecentCommandes();
}