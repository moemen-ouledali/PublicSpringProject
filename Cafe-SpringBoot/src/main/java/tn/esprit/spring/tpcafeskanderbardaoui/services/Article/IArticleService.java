package tn.esprit.spring.tpcafeskanderbardaoui.services.Article;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;

import java.util.List;

public interface IArticleService {

    // =============================
    //        CRUD METHODS
    // =============================
    ArticleResponse addArticle(ArticleRequest request);

    List<ArticleResponse> saveArticles(List<ArticleRequest> requests);

    ArticleResponse selectArticleById(long id);

    List<ArticleResponse> selectAllArticles();

    void deleteArticle(long id);

    void deleteAllArticles();

    long countingArticles();

    boolean verifArticleById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    // 1. Trouver les articles par nom exact
    List<ArticleResponse> findByNomArticleExact(String nom);

    // 2. Trouver les articles par type
    List<ArticleResponse> findByTypeArticle(TypeArticle type);

    // 3. Trouver les articles par prix exact
    List<ArticleResponse> findByPrixArticleExact(Double prix);

    // 4. Vérifier l'existence d'un article par nom
    boolean existsByNomArticle(String nom);

    // 5. Compter les articles par type
    long countByTypeArticle(TypeArticle type);

    // 6. Trouver les articles dont le nom contient un mot et sont d'un type spécifique
    List<ArticleResponse> findByNomContainingAndType(String mot, TypeArticle type);

    // 7. Trouver les articles avec un prix dans une plage et de types spécifiques
    List<ArticleResponse> findByPrixInRangeAndTypeIn(Double minPrix, Double maxPrix, List<TypeArticle> types);

    // 8. Trouver les articles dont le nom commence par (insensible à la casse), triés par prix
    List<ArticleResponse> findByNomStartingWithIgnoreCaseOrderByPrix(String prefix);

    // 9. Trouver les articles d'un type avec un prix maximum
    List<ArticleResponse> findByTypeAndPrixLessThanEqual(TypeArticle type, Double maxPrix);

    // 10. Trouver les articles par nom ou type, triés par prix décroissant
    List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(String nom, TypeArticle type);

    // 11. Trouver les articles dont le nom commence par un préfixe spécifique
    List<ArticleResponse> findByNomStartingWith(String prefix);

    // 12. Trouver les articles dont le nom se termine par un suffixe
    List<ArticleResponse> findByNomEndingWith(String suffix);

    // 13. Trouver les articles sans type renseigné
    List<ArticleResponse> findByTypeArticleIsNull();

    // 14. Trouver les articles avec un prix renseigné
    List<ArticleResponse> findByPrixArticleIsNotNull();

    // 15. Trouver les articles avec leurs promotions actives
    List<ArticleResponse> findArticlesWithActivePromotions();

    // 16. Trouver les articles avec nom contenant une chaine et prix dans une plage
    List<ArticleResponse> findByNomContainingAndPrixBetween(String nom, Double minPrix, Double maxPrix);

    ArticleResponse ajouterArticleEtPromotions(ArticleRequest request);

    void affecterPromotionAArticle(long idArticle, long idPromo);
    void desaffecterPromotionDUnArticle(long idArticle, long idPromo);
    Article ajouterArticleEtPromotions(Article article);
    void deleteArticleAndPromotions(Long idArticle);
    List<ArticleResponse> getArticlesWithPromotionThisMonth(int month, int year);



}