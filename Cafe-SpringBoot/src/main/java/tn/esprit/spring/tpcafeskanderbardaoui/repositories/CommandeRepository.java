package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.StatusCommande;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // 1. Trouver les commandes par statut
    @Query("SELECT c FROM Commande c WHERE c.statusCommande = :statut")
    List<Commande> findByStatut(@Param("statut") StatusCommande statut);

    // 2. Trouver les commandes par date exacte
    @Query("SELECT c FROM Commande c WHERE c.dateCommande = :date")
    List<Commande> findByDateExacte(@Param("date") LocalDate date);

    // 3. Compter les commandes par statut
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statusCommande = :statut")
    long countByStatut(@Param("statut") StatusCommande statut);

    // 4. Supprimer les commandes antérieures à une date
    @Modifying
    @Transactional
    @Query("DELETE FROM Commande c WHERE c.dateCommande < :date")
    int deleteByDateBefore(@Param("date") LocalDate date);

    // 5. Trouver les commandes entre deux dates avec un statut spécifique
    @Query("SELECT c FROM Commande c WHERE c.dateCommande BETWEEN :startDate AND :endDate AND c.statusCommande = :statut")
    List<Commande> findByDateBetweenAndStatut(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("statut") StatusCommande statut
    );

    // 6. Trouver les commandes avec un total supérieur à un montant et un statut différent
    @Query("SELECT c FROM Commande c WHERE c.totalCommande > :montant AND c.statusCommande != :statut")
    List<Commande> findByTotalGreaterThanAndStatutNot(
            @Param("montant") Double montant,
            @Param("statut") StatusCommande statut
    );

    // 7. Trouver les commandes avec certains statuts, triées par date
    @Query("SELECT c FROM Commande c WHERE c.statusCommande IN :statuts ORDER BY c.dateCommande DESC")
    List<Commande> findByStatutsInOrderByDate(@Param("statuts") List<StatusCommande> statuts);

    // 8. Trouver les commandes avant une date avec un total dans une plage
    @Query("SELECT c FROM Commande c WHERE c.dateCommande < :date AND c.totalCommande BETWEEN :minTotal AND :maxTotal")
    List<Commande> findByDateBeforeAndTotalBetween(
            @Param("date") LocalDate date,
            @Param("minTotal") Double minTotal,
            @Param("maxTotal") Double maxTotal
    );

    // 9. Trouver les commandes où le statut se termine par une lettre spécifique
    @Query("SELECT c FROM Commande c WHERE CAST(c.statusCommande AS string) LIKE CONCAT('%', :suffix)")
    List<Commande> findByStatutEndingWith(@Param("suffix") String suffix);

    // 10. Trouver les commandes sans statut renseigné
    @Query("SELECT c FROM Commande c WHERE c.statusCommande IS NULL")
    List<Commande> findByStatutIsNull();

    // 11. Trouver les commandes avec un total renseigné
    @Query("SELECT c FROM Commande c WHERE c.totalCommande IS NOT NULL")
    List<Commande> findByTotalIsNotNull();

    // 12. Trouver les commandes avec leurs détails et client
    @Query("SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.detailsCommande LEFT JOIN FETCH c.client")
    List<Commande> findAllWithDetailsAndClient();

    // 13. Trouver le top 3 des commandes les plus récentes
    @Query("SELECT c FROM Commande c ORDER BY c.dateCommande DESC")
    List<Commande> findTop3RecentCommandes();
}