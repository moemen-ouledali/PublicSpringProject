package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.StatusCommande;
import tn.esprit.spring.tpcafeskanderbardaoui.services.Commande.ICommandeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commandes")
public class CommandeRestController {

    private final ICommandeService commandeService;

    // =============================
    //        CRUD ENDPOINTS
    // =============================

    @GetMapping
    public List<CommandeResponce> selectAllCommandes() {
        return commandeService.selectAllCommandes();
    }

    @GetMapping("/{id}")
    public CommandeResponce selectCommandeById(@PathVariable long id) {
        return commandeService.selectCommandeById(id);
    }

    @PostMapping
    public CommandeResponce addCommande(@RequestBody CommandeRequest request) {
        return commandeService.addCommande(request);
    }

    @PostMapping("/batch")
    public List<CommandeResponce> saveCommandes(@RequestBody List<CommandeRequest> requests) {
        return commandeService.saveCommandes(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteCommandeById(@PathVariable long id) {
        commandeService.deleteCommandeById(id);
    }

    @DeleteMapping
    public void deleteAllCommandes() {
        commandeService.deleteAllCommandes();
    }

    @GetMapping("/count")
    public long countCommandes() {
        return commandeService.countingCommandes();
    }

    @GetMapping("/exists/{id}")
    public boolean verifCommandeById(@PathVariable long id) {
        return commandeService.verifCommandeById(id);
    }

    // =============================
    //    JPQL QUERY ENDPOINTS
    // =============================

    // 1. Trouver les commandes par statut
    @GetMapping("/search/statut")
    public List<CommandeResponce> findByStatut(@RequestParam StatusCommande statut) {
        return commandeService.findByStatut(statut);
    }

    // 2. Trouver les commandes par date exacte
    @GetMapping("/search/date-exacte")
    public List<CommandeResponce> findByDateExacte(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return commandeService.findByDateExacte(date);
    }

    // 3. Compter les commandes par statut
    @GetMapping("/count/statut")
    public long countByStatut(@RequestParam StatusCommande statut) {
        return commandeService.countByStatut(statut);
    }

    // 4. Supprimer les commandes antérieures à une date
    @DeleteMapping("/delete/before-date")
    public ResponseEntity<String> deleteByDateBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int deleted = commandeService.deleteByDateBefore(date);
        return ResponseEntity.ok(deleted + " commandes supprimées");
    }

    // 5. Trouver les commandes entre deux dates avec un statut spécifique
    @GetMapping("/search/date-between-statut")
    public List<CommandeResponce> findByDateBetweenAndStatut(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam StatusCommande statut) {
        return commandeService.findByDateBetweenAndStatut(startDate, endDate, statut);
    }

    // 6. Trouver les commandes avec un total supérieur à un montant et un statut différent
    @GetMapping("/search/total-greater-statut-not")
    public List<CommandeResponce> findByTotalGreaterThanAndStatutNot(
            @RequestParam Double montant,
            @RequestParam StatusCommande statut) {
        return commandeService.findByTotalGreaterThanAndStatutNot(montant, statut);
    }

    // 7. Trouver les commandes avec certains statuts, triées par date
    @GetMapping("/search/statuts-sorted")
    public List<CommandeResponce> findByStatutsInOrderByDate(@RequestParam List<StatusCommande> statuts) {
        return commandeService.findByStatutsInOrderByDate(statuts);
    }

    // 8. Trouver les commandes avant une date avec un total dans une plage
    @GetMapping("/search/date-before-total-between")
    public List<CommandeResponce> findByDateBeforeAndTotalBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Double minTotal,
            @RequestParam Double maxTotal) {
        return commandeService.findByDateBeforeAndTotalBetween(date, minTotal, maxTotal);
    }

    // 9. Trouver les commandes où le statut se termine par une lettre spécifique
    @GetMapping("/search/statut-ending")
    public List<CommandeResponce> findByStatutEndingWith(@RequestParam String suffix) {
        return commandeService.findByStatutEndingWith(suffix);
    }

    // 10. Trouver les commandes sans statut renseigné
    @GetMapping("/search/no-statut")
    public List<CommandeResponce> findByStatutIsNull() {
        return commandeService.findByStatutIsNull();
    }

    // 11. Trouver les commandes avec un total renseigné
    @GetMapping("/search/with-total")
    public List<CommandeResponce> findByTotalIsNotNull() {
        return commandeService.findByTotalIsNotNull();
    }

    // 12. Trouver les commandes avec leurs détails et client
    @GetMapping("/search/with-details-client")
    public List<CommandeResponce> findAllWithDetailsAndClient() {
        return commandeService.findAllWithDetailsAndClient();
    }

    // 13. Trouver le top 3 des commandes les plus récentes
    @GetMapping("/search/top3-recent")
    public List<CommandeResponce> findTop3RecentCommandes() {
        return commandeService.findTop3RecentCommandes();
    }
}