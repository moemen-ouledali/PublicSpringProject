package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IArticleMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.ArticleRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.services.Article.IArticleService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleRestController {

    private final IArticleService articleService;
    private final ArticleRepository articleRepo;
    private final IArticleMapper articleMapper;

    // =============================
    //        CRUD ENDPOINTS
    // =============================

    // ✅ Get all articles
    @GetMapping
    public List<ArticleResponse> selectAllArticles() {
        return articleService.selectAllArticles();
    }

    // ✅ Get article by ID
    @GetMapping("/{id}")
    public ArticleResponse selectArticleById(@PathVariable long id) {
        return articleService.selectArticleById(id);
    }

    // ✅ Add one article
    @PostMapping
    public ArticleResponse addArticle(@RequestBody ArticleRequest request) {
        return articleService.addArticle(request);
    }

    // ✅ Add multiple articles
    @PostMapping("/batch")
    public List<ArticleResponse> saveArticles(@RequestBody List<ArticleRequest> requests) {
        return articleService.saveArticles(requests);
    }

    // ✅ Delete article by ID
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleService.deleteArticle(id);
    }

    // ✅ Delete all articles
    @DeleteMapping
    public void deleteAllArticles() {
        articleService.deleteAllArticles();
    }

    // ✅ Count all articles
    @GetMapping("/count")
    public long countArticles() {
        return articleService.countingArticles();
    }

    // ✅ Check if article exists by ID
    @GetMapping("/exists/{id}")
    public boolean verifArticleById(@PathVariable long id) {
        return articleService.verifArticleById(id);
    }

    // =============================
    //    JPQL QUERY ENDPOINTS
    // =============================

    // 1. Trouver les articles par nom exact
    @GetMapping("/search/nom-exact")
    public List<ArticleResponse> findByNomArticleExact(@RequestParam String nom) {
        return articleService.findByNomArticleExact(nom);
    }

    // 2. Trouver les articles par type
    @GetMapping("/search/type")
    public List<ArticleResponse> findByTypeArticle(@RequestParam TypeArticle type) {
        return articleService.findByTypeArticle(type);
    }

    // 3. Trouver les articles par prix exact
    @GetMapping("/search/prix-exact")
    public List<ArticleResponse> findByPrixArticleExact(@RequestParam Double prix) {
        return articleService.findByPrixArticleExact(prix);
    }

    // 4. Vérifier l'existence d'un article par nom
    @GetMapping("/exists/nom")
    public boolean existsByNomArticle(@RequestParam String nom) {
        return articleService.existsByNomArticle(nom);
    }

    // 5. Compter les articles par type
    @GetMapping("/count/type")
    public long countByTypeArticle(@RequestParam TypeArticle type) {
        return articleService.countByTypeArticle(type);
    }

    // 6. Trouver les articles dont le nom contient un mot et sont d'un type spécifique
    @GetMapping("/search/nom-and-type")
    public List<ArticleResponse> findByNomContainingAndType(
            @RequestParam String mot,
            @RequestParam TypeArticle type) {
        return articleService.findByNomContainingAndType(mot, type);
    }

    // 7. Trouver les articles avec un prix dans une plage et de types spécifiques
    @GetMapping("/search/prix-range-types")
    public List<ArticleResponse> findByPrixInRangeAndTypeIn(
            @RequestParam Double minPrix,
            @RequestParam Double maxPrix,
            @RequestParam List<TypeArticle> types) {
        return articleService.findByPrixInRangeAndTypeIn(minPrix, maxPrix, types);
    }

    // 8. Trouver les articles dont le nom commence par (insensible à la casse), triés par prix
    @GetMapping("/search/nom-starts-sorted")
    public List<ArticleResponse> findByNomStartingWithIgnoreCaseOrderByPrix(@RequestParam String prefix) {
        return articleService.findByNomStartingWithIgnoreCaseOrderByPrix(prefix);
    }

    // 9. Trouver les articles d'un type avec un prix maximum
    @GetMapping("/search/type-prix-max")
    public List<ArticleResponse> findByTypeAndPrixLessThanEqual(
            @RequestParam TypeArticle type,
            @RequestParam Double maxPrix) {
        return articleService.findByTypeAndPrixLessThanEqual(type, maxPrix);
    }

    // 10. Trouver les articles par nom ou type, triés par prix décroissant
    @GetMapping("/search/nom-or-type-sorted")
    public List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(
            @RequestParam String nom,
            @RequestParam TypeArticle type) {
        return articleService.findByNomOrTypeOrderByPrixDesc(nom, type);
    }

    // 11. Trouver les articles dont le nom commence par un préfixe spécifique
    @GetMapping("/search/nom-starts")
    public List<ArticleResponse> findByNomStartingWith(@RequestParam String prefix) {
        return articleService.findByNomStartingWith(prefix);
    }

    // 12. Trouver les articles dont le nom se termine par un suffixe
    @GetMapping("/search/nom-ends")
    public List<ArticleResponse> findByNomEndingWith(@RequestParam String suffix) {
        return articleService.findByNomEndingWith(suffix);
    }

    // 13. Trouver les articles sans type renseigné
    @GetMapping("/search/no-type")
    public List<ArticleResponse> findByTypeArticleIsNull() {
        return articleService.findByTypeArticleIsNull();
    }

    // 14. Trouver les articles avec un prix renseigné
    @GetMapping("/search/with-prix")
    public List<ArticleResponse> findByPrixArticleIsNotNull() {
        return articleService.findByPrixArticleIsNotNull();
    }

    // 15. Trouver les articles avec leurs promotions actives
    @GetMapping("/search/with-active-promotions")
    public List<ArticleResponse> findArticlesWithActivePromotions() {
        return articleService.findArticlesWithActivePromotions();
    }

    // 16. Trouver les articles avec nom contenant une chaine et prix dans une plage
    @GetMapping("/search/nom-prix-range")
    public List<ArticleResponse> findByNomContainingAndPrixBetween(
            @RequestParam String nom,
            @RequestParam Double minPrix,
            @RequestParam Double maxPrix) {
        return articleService.findByNomContainingAndPrixBetween(nom, minPrix, maxPrix);
    }
    @PostMapping("/with-promotions")
    public ResponseEntity<ArticleResponse> ajouterArticleEtPromotions(@RequestBody ArticleRequest request) {
        ArticleResponse response = articleService.ajouterArticleEtPromotions(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/affecter-promo/{idArticle}/{idPromo}")
    public ResponseEntity<String> affecterPromotionAArticle(
            @PathVariable Long idArticle,
            @PathVariable Long idPromo) {
        articleService.affecterPromotionAArticle(idArticle, idPromo);
        return ResponseEntity.ok("Promotion affectée à l'article avec succès");
    }

    @DeleteMapping("/desaffecter-promo/{idArticle}/{idPromo}")
    public ResponseEntity<String> desaffecterPromotionDUnArticle(
            @PathVariable Long idArticle,
            @PathVariable Long idPromo) {
        articleService.desaffecterPromotionDUnArticle(idArticle, idPromo);
        return ResponseEntity.ok("Promotion désaffectée de l'article avec succès");
    }

    @PostMapping("/add-with-promotions")
    public ResponseEntity<Article> ajouterArticleEtPromotions(@RequestBody Article article) {
        Article savedArticle = articleService.ajouterArticleEtPromotions(article);
        return ResponseEntity.ok(savedArticle);
    }


    @DeleteMapping("/delete-article-with-promotions/{idArticle}")
    public ResponseEntity<String> deleteArticleWithPromotions(@PathVariable Long idArticle) {

        articleService.deleteArticleAndPromotions(idArticle);

        return ResponseEntity.ok("Article et promotions supprimés avec succès");
    }


    @GetMapping("/promotions/current-month")
    public List<ArticleResponse> getArticlesWithPromotionThisMonth() {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        return articleService.getArticlesWithPromotionThisMonth(currentMonth, currentYear);
    }

}