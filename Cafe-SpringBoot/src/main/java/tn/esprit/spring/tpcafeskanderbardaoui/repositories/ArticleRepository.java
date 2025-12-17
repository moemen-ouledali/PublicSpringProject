package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;

import java.util.List;
import java.util.Optional;

public interface    ArticleRepository extends JpaRepository<Article, Long> {

    // 1. Trouver les articles par nom exact
    @Query("SELECT a FROM Article a WHERE a.nomArticle = :nom")
    List<Article> findByNomArticleExact(@Param("nom") String nom);

    // 2. Trouver les articles par type
    @Query("SELECT a FROM Article a WHERE a.typeArticle = :type")
    List<Article> findByTypeArticle(@Param("type") TypeArticle type);

    // 3. Trouver les articles par prix exact
    @Query("SELECT a FROM Article a WHERE a.prixArticle = :prix")
    List<Article> findByPrixArticleExact(@Param("prix") Double prix);

    // 4. Vérifier l'existence d'un article par nom
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Article a WHERE a.nomArticle = :nom")
    boolean existsByNomArticle(@Param("nom") String nom);

    // 5. Compter les articles par type
    @Query("SELECT COUNT(a) FROM Article a WHERE a.typeArticle = :type")
    long countByTypeArticle(@Param("type") TypeArticle type);

    // 6. Trouver les articles dont le nom contient un mot et sont d'un type spécifique
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :mot, '%')) AND a.typeArticle = :type")
    List<Article> findByNomContainingAndType(@Param("mot") String mot, @Param("type") TypeArticle type);

    // 7. Trouver les articles avec un prix dans une plage et de types spécifiques
    @Query("SELECT a FROM Article a WHERE a.prixArticle BETWEEN :minPrix AND :maxPrix AND a.typeArticle IN :types")
    List<Article> findByPrixInRangeAndTypeIn(
            @Param("minPrix") Double minPrix,
            @Param("maxPrix") Double maxPrix,
            @Param("types") List<TypeArticle> types
    );

    // 8. Trouver les articles dont le nom commence par (insensible à la casse), triés par prix
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY a.prixArticle ASC")
    List<Article> findByNomStartingWithIgnoreCaseOrderByPrix(@Param("prefix") String prefix);

    // 9. Trouver les articles d'un type avec un prix maximum
    @Query("SELECT a FROM Article a WHERE a.typeArticle = :type AND a.prixArticle <= :maxPrix")
    List<Article> findByTypeAndPrixLessThanEqual(@Param("type") TypeArticle type, @Param("maxPrix") Double maxPrix);

    // 10. Trouver les articles par nom ou type, triés par prix décroissant
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :nom, '%')) OR a.typeArticle = :type ORDER BY a.prixArticle DESC")
    List<Article> findByNomOrTypeOrderByPrixDesc(@Param("nom") String nom, @Param("type") TypeArticle type);

    // 11. Trouver les articles dont le nom commence par un préfixe spécifique
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT(:prefix, '%')")
    List<Article> findByNomStartingWith(@Param("prefix") String prefix);

    // 12. Trouver les articles dont le nom se termine par un suffixe
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT('%', :suffix)")
    List<Article> findByNomEndingWith(@Param("suffix") String suffix);

    // 13. Trouver les articles sans type renseigné
    @Query("SELECT a FROM Article a WHERE a.typeArticle IS NULL")
    List<Article> findByTypeArticleIsNull();

    // 14. Trouver les articles avec un prix renseigné
    @Query("SELECT a FROM Article a WHERE a.prixArticle IS NOT NULL")
    List<Article> findByPrixArticleIsNotNull();

    // 15. Trouver les articles avec leurs promotions actives
// 15. Trouver les articles avec leurs promotions actives (basées sur les dates)
    @Query("SELECT DISTINCT a FROM Article a JOIN a.promotions p WHERE CURRENT_DATE BETWEEN p.dateDebutPromo AND p.dateFinPromo")
    List<Article> findArticlesWithActivePromotions();

    // 16. Trouver les articles avec nom contenant une chaine et prix dans une plage
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :nom, '%')) AND a.prixArticle BETWEEN :minPrix AND :maxPrix")
    List<Article> findByNomContainingAndPrixBetween(
            @Param("nom") String nom,
            @Param("minPrix") Double minPrix,
            @Param("maxPrix") Double maxPrix
    );

    @Query("SELECT DISTINCT a FROM Article a JOIN a.promotions p " +
            "WHERE (MONTH(p.dateDebutPromo) = :currentMonth OR MONTH(p.dateFinPromo) = :currentMonth) " +
            "AND (YEAR(p.dateDebutPromo) = :currentYear OR YEAR(p.dateFinPromo) = :currentYear)")
    List<Article> findArticlesWithPromotionInCurrentMonth(@Param("currentMonth") int currentMonth,
                                                          @Param("currentYear") int currentYear);

}
