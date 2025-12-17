package tn.esprit.spring.tpcafeskanderbardaoui.services.Article;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ArticleDTO.ArticleResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Promotion;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.TypeArticle;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IArticleMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.ArticleRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.PromotionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepo;
    private final IArticleMapper articleMapper;
    private final PromotionRepository promotionRepository;



    // =============================
    //        CRUD METHODS
    // =============================

    @Override
    public ArticleResponse addArticle(ArticleRequest request) {
        Article article = articleMapper.toEntity(request);
        Article saved = articleRepo.save(article);
        return articleMapper.toResponse(saved);
    }

    @Override
    public List<ArticleResponse> saveArticles(List<ArticleRequest> requests) {
        List<Article> articles = requests.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList());
        return articleRepo.saveAll(articles).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponse selectArticleById(long id) {
        Optional<Article> article = articleRepo.findById(id);
        return article.map(articleMapper::toResponse).orElse(null);
    }

    @Override
    public List<ArticleResponse> selectAllArticles() {
        return articleRepo.findAll().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(long id) {
        articleRepo.deleteById(id);
    }

    @Override
    public void deleteAllArticles() {
        articleRepo.deleteAll();
    }

    @Override
    public long countingArticles() {
        return articleRepo.count();
    }

    @Override
    public boolean verifArticleById(long id) {
        return articleRepo.existsById(id);
    }

    // =============================
    //    JPQL QUERY METHODS
    // =============================

    @Override
    public List<ArticleResponse> findByNomArticleExact(String nom) {
        return articleRepo.findByNomArticleExact(nom).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByTypeArticle(TypeArticle type) {
        return articleRepo.findByTypeArticle(type).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByPrixArticleExact(Double prix) {
        return articleRepo.findByPrixArticleExact(prix).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNomArticle(String nom) {
        return articleRepo.existsByNomArticle(nom);
    }

    @Override
    public long countByTypeArticle(TypeArticle type) {
        return articleRepo.countByTypeArticle(type);
    }

    @Override
    public List<ArticleResponse> findByNomContainingAndType(String mot, TypeArticle type) {
        return articleRepo.findByNomContainingAndType(mot, type).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByPrixInRangeAndTypeIn(Double minPrix, Double maxPrix, List<TypeArticle> types) {
        return articleRepo.findByPrixInRangeAndTypeIn(minPrix, maxPrix, types).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomStartingWithIgnoreCaseOrderByPrix(String prefix) {
        return articleRepo.findByNomStartingWithIgnoreCaseOrderByPrix(prefix).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByTypeAndPrixLessThanEqual(TypeArticle type, Double maxPrix) {
        return articleRepo.findByTypeAndPrixLessThanEqual(type, maxPrix).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(String nom, TypeArticle type) {
        return articleRepo.findByNomOrTypeOrderByPrixDesc(nom, type).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomStartingWith(String prefix) {
        return articleRepo.findByNomStartingWith(prefix).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomEndingWith(String suffix) {
        return articleRepo.findByNomEndingWith(suffix).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByTypeArticleIsNull() {
        return articleRepo.findByTypeArticleIsNull().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByPrixArticleIsNotNull() {
        return articleRepo.findByPrixArticleIsNotNull().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findArticlesWithActivePromotions() {
        return articleRepo.findArticlesWithActivePromotions().stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomContainingAndPrixBetween(String nom, Double minPrix, Double maxPrix) {
        return articleRepo.findByNomContainingAndPrixBetween(nom, minPrix, maxPrix).stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());
    }



    @Override
    public ArticleResponse ajouterArticleEtPromotions(ArticleRequest request) {
        Article article = articleMapper.toEntity(request);

        // ✅ Use lowercase 'promotionRepository' - the injected instance
        if (request.getPromotionIds() != null && !request.getPromotionIds().isEmpty()) {
            List<Promotion> promotions = promotionRepository.findAllById(request.getPromotionIds());

            if (promotions.size() != request.getPromotionIds().size()) {
                throw new RuntimeException("One or more promotions not found");
            }

            article.setPromotions(promotions);
            for (Promotion promotion : promotions) {
                if (promotion.getArticles() == null) {
                    promotion.setArticles(new ArrayList<>());
                }
                promotion.getArticles().add(article);
            }
        }

        Article savedArticle = articleRepo.save(article);
        return articleMapper.toResponse(savedArticle);
    }


    @Override
    public void affecterPromotionAArticle(long idArticle, long idPromo) {
        Promotion promotion = promotionRepository.findById(idPromo).get();
        Article article = articleRepo.findById(idArticle).get();
        article.getPromotions().add(promotion);
        articleRepo.save(article);
    }

    @Override
    public void desaffecterPromotionDUnArticle(long idArticle, long idPromo) {
        Article article = articleRepo.findById(idArticle).get();
        Promotion promotion = promotionRepository.findById(idPromo).get();
        article.getPromotions().remove(promotion);
        articleRepo.save(article);

    }
    @Override
    public Article ajouterArticleEtPromotions(Article article) {

        if (article.getPromotions() != null && !article.getPromotions().isEmpty()) {
            // Sauvegarder d'abord les promotions
            List<Promotion> savedPromotions = new ArrayList<>();

            for (Promotion promo : article.getPromotions()) {
                Promotion savedPromo = promotionRepository.save(promo);
                savedPromotions.add(savedPromo);
            }

            // Associer les promotions sauvegardées à l'article
            article.setPromotions(savedPromotions);
        }

        // Sauvegarder l'article avec les promotions
        return articleRepo.save(article);
    }


    @Transactional
    @Override
    public void deleteArticleAndPromotions(Long idArticle) {

        Article article = articleRepo.findById(idArticle)
                .orElseThrow(() -> new RuntimeException("Article introuvable"));

        if (article.getPromotions() != null) {
            article.getPromotions().forEach(promo -> {
                promo.getArticles().remove(article); // couper la relation

                // si tu veux supprimer la promotion complètement :
                if (promo.getArticles().isEmpty()) {
                    promotionRepository.delete(promo);
                }
            });

            article.getPromotions().clear();
        }

        articleRepo.delete(article);
    }

    @Override
    @Scheduled(cron = "0 0 0 1 * *") // 0h00, le 1er de chaque mois
    public List<ArticleResponse> getArticlesWithPromotionThisMonth(int month, int year) {
        // Si le scheduler appelle la méthode sans paramètres, on utilise le mois et l'année actuels
        if (month == 0 || year == 0) {
            LocalDate today = LocalDate.now();
            month = today.getMonthValue();
            year = today.getYear();
        }

        List<ArticleResponse> articles = articleRepo.findArticlesWithPromotionInCurrentMonth(month, year)
                .stream()
                .map(articleMapper::toResponse)
                .collect(Collectors.toList());

        // Log des articles
        if (articles.isEmpty()) {
            log.info("📦 Pas d'articles en promotion ce mois-ci.");
        } else {
            log.info("📦 Articles en promotion pour {}-{}:", month, year);
            articles.forEach(a -> log.info("- {} (Prix: {})", a.getNomArticle(), a.getPrixArticle()));
        }

        return articles;
    }



}