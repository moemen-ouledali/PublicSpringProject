package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.services.CarteFidelite.ICarteFideliteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartes-fidelite")
public class CarteFideliteRestController {

    private final ICarteFideliteService carteFideliteService;

    // =============================
    //        CRUD ENDPOINTS
    // =============================

    @GetMapping
    public List<CarteFideliteResponce> selectAllCarteFidelites() {
        return carteFideliteService.selectAllCarteFidelites();
    }

    @GetMapping("/{id}")
    public CarteFideliteResponce selectCarteFideliteById(@PathVariable long id) {
        return carteFideliteService.selectCarteFideliteById(id);
    }

    @PostMapping
    public CarteFideliteResponce addCarteFidelite(@RequestBody CarteFideliteRequest request) {
        return carteFideliteService.addCarteFidelite(request);
    }

    @PostMapping("/batch")
    public List<CarteFideliteResponce> saveCarteFidelites(@RequestBody List<CarteFideliteRequest> requests) {
        return carteFideliteService.saveCarteFidelites(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteCarteFideliteById(@PathVariable long id) {
        carteFideliteService.deleteCarteFideliteById(id);
    }

    @DeleteMapping
    public void deleteAllCarteFidelites() {
        carteFideliteService.deleteAllCarteFidelites();
    }

    @GetMapping("/count")
    public long countCarteFidelites() {
        return carteFideliteService.countingCarteFidelites();
    }

    @GetMapping("/exists/{id}")
    public boolean verifCarteFideliteById(@PathVariable long id) {
        return carteFideliteService.verifCarteFideliteById(id);
    }

    // =============================
    //    JPQL QUERY ENDPOINTS
    // =============================

    // 1. Trouver les cartes avec un nombre exact de points
    @GetMapping("/search/points-exact")
    public List<CarteFideliteResponce> findByPointsExact(@RequestParam Integer points) {
        return carteFideliteService.findByPointsExact(points);
    }

    // 2. Trouver les cartes créées à une date spécifique
    @GetMapping("/search/by-date")
    public List<CarteFideliteResponce> findByDateCreation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return carteFideliteService.findByDateCreation(date);
    }

    // 3. Compter les cartes avec plus de X points
    @GetMapping("/count/points-greater-than")
    public long countByPointsGreaterThan(@RequestParam Integer points) {
        return carteFideliteService.countByPointsGreaterThan(points);
    }

    // 4. Supprimer les cartes créées avant une date
    @DeleteMapping("/delete/before-date")
    public ResponseEntity<String> deleteByDateCreationBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int deleted = carteFideliteService.deleteByDateCreationBefore(date);
        return ResponseEntity.ok(deleted + " cartes supprimées");
    }

    // 5. Trouver les cartes avec des points dans une plage, créées après une date
    @GetMapping("/search/points-range-after-date")
    public List<CarteFideliteResponce> findByPointsInRangeAndDateAfter(
            @RequestParam Integer minPoints,
            @RequestParam Integer maxPoints,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return carteFideliteService.findByPointsInRangeAndDateAfter(minPoints, maxPoints, date);
    }

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    @GetMapping("/search/points-min-sorted")
    public List<CarteFideliteResponce> findByPointsGreaterThanEqualOrderByDateCreation(
            @RequestParam Integer minPoints) {
        return carteFideliteService.findByPointsGreaterThanEqualOrderByDateCreation(minPoints);
    }

    // 7. Trouver les cartes créées entre deux dates
    @GetMapping("/search/date-between")
    public List<CarteFideliteResponce> findByDateCreationBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return carteFideliteService.findByDateCreationBetween(startDate, endDate);
    }

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    @GetMapping("/search/low-points-or-old")
    public List<CarteFideliteResponce> findByLowPointsOrDateBefore(
            @RequestParam Integer maxPoints,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return carteFideliteService.findByLowPointsOrDateBefore(maxPoints, date);
    }

    // 9. Trouver la carte avec le plus de points
    @GetMapping("/search/max-points")
    public ResponseEntity<CarteFideliteResponce> findCardWithMaxPoints() {
        CarteFideliteResponce carte = carteFideliteService.findCardWithMaxPoints();
        return carte != null ? ResponseEntity.ok(carte) : ResponseEntity.notFound().build();
    }

    // 10. Trouver les cartes sans date de création
    @GetMapping("/search/no-date")
    public List<CarteFideliteResponce> findByDateCreationIsNull() {
        return carteFideliteService.findByDateCreationIsNull();
    }

    // 11. Trouver les cartes avec des points accumulés renseignés
    @GetMapping("/search/with-points")
    public List<CarteFideliteResponce> findByPointsIsNotNull() {
        return carteFideliteService.findByPointsIsNotNull();
    }

    // 12. Trouver les cartes avec leur client propriétaire (Par nom et prénom)
    @GetMapping("/search/by-client")
    public List<CarteFideliteResponce> findByClientNomAndPrenom(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return carteFideliteService.findByClientNomAndPrenom(nom, prenom);
    }

    // 13. Trouver top 5 des cartes avec le plus de points
    @GetMapping("/search/top5")
    public List<CarteFideliteResponce> findTop5ByOrderByPointsDesc() {
        return carteFideliteService.findTop5ByOrderByPointsDesc();
    }
}