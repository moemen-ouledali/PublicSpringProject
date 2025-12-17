package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.services.Detail_Commande.Detail_CommandeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/detail-commandes")
public class Detail_CommandeRestController {

    private final Detail_CommandeService detailService;

    // ================================
    //        CRUD ENDPOINTS
    // ================================
    @GetMapping
    public List<DetailCommandeResponce> getAllDetails() {
        return detailService.selectAllDetailsCommande();
    }

    @GetMapping("/{id}")
    public DetailCommandeResponce getDetailById(@PathVariable long id) {
        return detailService.selectDetailCommandeById(id);
    }

    @PostMapping
    public DetailCommandeResponce addDetail(@RequestBody DetailCommandeRequest request) {
        return detailService.addDetailCommande(request);
    }

    @PostMapping("/batch")
    public List<DetailCommandeResponce> addDetailsBatch(@RequestBody List<DetailCommandeRequest> requests) {
        return detailService.saveDetailsCommande(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteDetailById(@PathVariable long id) {
        detailService.deleteDetailCommandeById(id);
    }

    @DeleteMapping
    public void deleteAllDetails() {
        detailService.deleteAllDetailsCommande();
    }

    @GetMapping("/count")
    public long countDetails() {
        return detailService.countingDetailsCommande();
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return detailService.verifDetailCommandeById(id);
    }

    // ================================
    //        JPQL QUERY ENDPOINTS
    // ================================
    @GetMapping("/search/quantite-exact")
    public List<DetailCommandeResponce> findByQuantiteExact(@RequestParam Integer quantite) {
        return detailService.findByQuantiteExact(quantite);
    }

    @GetMapping("/search/sous-total-exact")
    public List<DetailCommandeResponce> findBySousTotalExact(@RequestParam Double sousTotal) {
        return detailService.findBySousTotalExact(sousTotal);
    }

    @GetMapping("/count/quantite-greater")
    public long countByQuantiteGreaterThan(@RequestParam Integer quantite) {
        return detailService.countByQuantiteGreaterThan(quantite);
    }

    @GetMapping("/exists/sous-total-greater")
    public boolean existsBySousTotalGreaterThan(@RequestParam Double montant) {
        return detailService.existsBySousTotalGreaterThan(montant);
    }

    @GetMapping("/search/quantite-between-sous-total-min")
    public List<DetailCommandeResponce> findByQuantiteBetweenAndSousTotalMin(
            @RequestParam Integer minQte,
            @RequestParam Integer maxQte,
            @RequestParam Double minSousTotal) {
        return detailService.findByQuantiteBetweenAndSousTotalMin(minQte, maxQte, minSousTotal);
    }

    @GetMapping("/search/sous-total-between-orderby-quantite")
    public List<DetailCommandeResponce> findBySousTotalBetweenOrderByQuantite(
            @RequestParam Double minSousTotal,
            @RequestParam Double maxSousTotal) {
        return detailService.findBySousTotalBetweenOrderByQuantite(minSousTotal, maxSousTotal);
    }

    @GetMapping("/search/sous-total-apres-promo-between")
    public List<DetailCommandeResponce> findBySousTotalApresPromoBetween(
            @RequestParam Double min,
            @RequestParam Double max) {
        return detailService.findBySousTotalApresPromoBetween(min, max);
    }

    @GetMapping("/search/quantite-or-sous-total-min")
    public List<DetailCommandeResponce> findByQuantiteOrSousTotalMin(
            @RequestParam Integer quantite,
            @RequestParam Double minSousTotal) {
        return detailService.findByQuantiteOrSousTotalMin(quantite, minSousTotal);
    }

    @GetMapping("/search/top5-sous-total")
    public List<DetailCommandeResponce> findTop5BySousTotalDesc() {
        return detailService.findTop5BySousTotalDesc();
    }

    @GetMapping("/search/quantite-null")
    public List<DetailCommandeResponce> findByQuantiteIsNull() {
        return detailService.findByQuantiteIsNull();
    }

    @GetMapping("/search/sous-total-apres-promo-not-null")
    public List<DetailCommandeResponce> findBySousTotalApresPromoIsNotNull() {
        return detailService.findBySousTotalApresPromoIsNotNull();
    }

    @GetMapping("/search/with-commande-and-article")
    public List<DetailCommandeResponce> findAllWithCommandeAndArticle() {
        return detailService.findAllWithCommandeAndArticle();
    }
}
