package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Promotion;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IPromotionMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.services.Promotion.IPromotionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotions")
public class PromotionRestController {

    private final IPromotionService promotionService;
    private final IPromotionMapper promotionMapper;

    // =============================
    //        CRUD ENDPOINTS
    // =============================
    @GetMapping
    public ResponseEntity<List<PromotionResponce>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.selectAllPromotions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponce> getPromotionById(@PathVariable long id) {
        try {
            PromotionResponce promotion = promotionService.selectPromotionById(id);
            return ResponseEntity.ok(promotion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PromotionResponce> createPromotion(@RequestBody PromotionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(promotionService.addPromotion(request));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<PromotionResponce>> createPromotions(@RequestBody List<PromotionRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(promotionService.savePromotions(requests));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionResponce> updatePromotion(
            @PathVariable long id,
            @RequestBody PromotionRequest request) {
        if (!promotionService.verifPromotionById(id)) {
            return ResponseEntity.notFound().build();
        }
        request.setIdPromotion(id);
        PromotionResponce updated = promotionService.addPromotion(request); // addPromotion will save/update
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable long id) {
        if (!promotionService.verifPromotionById(id)) {
            return ResponseEntity.notFound().build();
        }
        promotionService.deletePromotionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPromotions() {
        promotionService.deleteAllPromotions();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPromotions() {
        return ResponseEntity.ok(promotionService.countingPromotions());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsPromotionById(@PathVariable long id) {
        return ResponseEntity.ok(promotionService.verifPromotionById(id));
    }

    // =============================
    //    JPQL QUERY ENDPOINTS
    // =============================
    @GetMapping("/search/pourcentage-exact")
    public ResponseEntity<List<PromotionResponce>> findByPourcentageExact(@RequestParam Double pourcentage) {
        return ResponseEntity.ok(promotionService.findByPourcentageExact(pourcentage));
    }

    @GetMapping("/search/date-debut")
    public ResponseEntity<List<PromotionResponce>> findByDateDebut(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(promotionService.findByDateDebut(date));
    }

    @GetMapping("/search/date-fin")
    public ResponseEntity<List<PromotionResponce>> findByDateFin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(promotionService.findByDateFin(date));
    }

    @GetMapping("/exists/pourcentage")
    public ResponseEntity<Boolean> existsByPourcentage(@RequestParam Double pourcentage) {
        return ResponseEntity.ok(promotionService.existsByPourcentage(pourcentage));
    }

    @GetMapping("/count/debut-after")
    public ResponseEntity<Long> countByDateDebutAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(promotionService.countByDateDebutAfter(date));
    }

    @GetMapping("/search/active-at")
    public ResponseEntity<List<PromotionResponce>> findPromotionsActiveAt(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(promotionService.findPromotionsActiveAt(date));
    }

    @GetMapping("/search/pourcentage-debut-between")
    public ResponseEntity<List<PromotionResponce>> findByPourcentageAndDateDebutBetween(
            @RequestParam Double pourcentage,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(promotionService.findByPourcentageAndDateDebutBetween(pourcentage, startDate, endDate));
    }

    @GetMapping("/search/valid-at")
    public ResponseEntity<List<PromotionResponce>> findPromotionsValidAt(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(promotionService.findPromotionsValidAt(date));
    }

    @GetMapping("/search/pourcentages-sorted")
    public ResponseEntity<List<PromotionResponce>> findByPourcentagesInOrderByDateDebut(
            @RequestParam List<Double> pourcentages) {
        return ResponseEntity.ok(promotionService.findByPourcentagesInOrderByDateDebut(pourcentages));
    }

    @GetMapping("/search/active-sorted-by-pourcentage")
    public ResponseEntity<List<PromotionResponce>> findActivePromotionsOrderByPourcentage() {
        return ResponseEntity.ok(promotionService.findActivePromotionsOrderByPourcentage());
    }

    @GetMapping("/search/no-date-fin")
    public ResponseEntity<List<PromotionResponce>> findByDateFinIsNull() {
        return ResponseEntity.ok(promotionService.findByDateFinIsNull());
    }

    @GetMapping("/search/with-pourcentage")
    public ResponseEntity<List<PromotionResponce>> findByPourcentageIsNotNull() {
        return ResponseEntity.ok(promotionService.findByPourcentageIsNotNull());
    }

    @GetMapping("/search/with-articles")
    public ResponseEntity<List<PromotionResponce>> findAllWithArticles() {
        return ResponseEntity.ok(promotionService.findAllWithArticles());
    }

    @GetMapping("/search/expired")
    public ResponseEntity<List<PromotionResponce>> findExpiredPromotions() {
        return ResponseEntity.ok(promotionService.findExpiredPromotions());
    }

    // =============================
    //    NEW METHOD: Add & Attach
    // =============================
    @PostMapping("/add-and-attach/{idArticle}")
    public ResponseEntity<String> createAndAttachPromotion(
            @RequestBody PromotionRequest request,
            @PathVariable long idArticle) {
        Promotion promotion = promotionMapper.toEntity(request);
        promotionService.ajouterPromoEtAffecterAArticle(request, idArticle);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Promotion created and attached to article with ID " + idArticle);
    }
}
