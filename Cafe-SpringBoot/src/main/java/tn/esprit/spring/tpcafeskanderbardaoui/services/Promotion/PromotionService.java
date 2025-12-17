package tn.esprit.spring.tpcafeskanderbardaoui.services.Promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.PromotionDTO.PromotionResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Promotion;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IPromotionMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.ArticleRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.PromotionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PromotionService implements IPromotionService {

    private final PromotionRepository promotionRepository;
    private final IPromotionMapper promotionMapper;
    private final ArticleRepository articleRepository;


    // =============================
    //        CRUD METHODS
    // =============================
    @Override
    public PromotionResponce addPromotion(PromotionRequest request) {
        Promotion promotion = promotionMapper.toEntity(request);
        Promotion savedPromotion = promotionRepository.save(promotion);
        return promotionMapper.toResponse(savedPromotion);
    }

    @Override
    public List<PromotionResponce> savePromotions(List<PromotionRequest> requests) {
        List<Promotion> promotions = requests.stream()
                .map(promotionMapper::toEntity)
                .collect(Collectors.toList());
        List<Promotion> savedPromotions = promotionRepository.saveAll(promotions);
        return promotionMapper.toResponseList(savedPromotions);
    }

    @Override
    public PromotionResponce selectPromotionById(long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
        return promotionMapper.toResponse(promotion);
    }

    @Override
    public List<PromotionResponce> selectAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotionMapper.toResponseList(promotions);
    }

    @Override
    public void deletePromotionById(long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public void deleteAllPromotions() {
        promotionRepository.deleteAll();
    }

    @Override
    public long countingPromotions() {
        return promotionRepository.count();
    }

    @Override
    public boolean verifPromotionById(long id) {
        return promotionRepository.existsById(id);
    }

    // =============================
    //        JPQL QUERY METHODS
    // =============================
    @Override
    public List<PromotionResponce> findByPourcentageExact(Double pourcentage) {
        return promotionMapper.toResponseList(promotionRepository.findByPourcentageExact(pourcentage));
    }

    @Override
    public List<PromotionResponce> findByDateDebut(LocalDate date) {
        return promotionMapper.toResponseList(promotionRepository.findByDateDebut(date));
    }

    @Override
    public List<PromotionResponce> findByDateFin(LocalDate date) {
        return promotionMapper.toResponseList(promotionRepository.findByDateFin(date));
    }

    @Override
    public boolean existsByPourcentage(Double pourcentage) {
        return promotionRepository.existsByPourcentage(pourcentage);
    }

    @Override
    public long countByDateDebutAfter(LocalDate date) {
        return promotionRepository.countByDateDebutAfter(date);
    }

    @Override
    public List<PromotionResponce> findPromotionsActiveAt(LocalDate date) {
        return promotionMapper.toResponseList(promotionRepository.findPromotionsActiveAt(date));
    }

    @Override
    public List<PromotionResponce> findByPourcentageAndDateDebutBetween(Double pourcentage, LocalDate startDate, LocalDate endDate) {
        return promotionMapper.toResponseList(promotionRepository.findByPourcentageAndDateDebutBetween(pourcentage, startDate, endDate));
    }

    @Override
    public List<PromotionResponce> findPromotionsValidAt(LocalDate date) {
        return promotionMapper.toResponseList(promotionRepository.findPromotionsValidAt(date));
    }

    @Override
    public List<PromotionResponce> findByPourcentagesInOrderByDateDebut(List<Double> pourcentages) {
        return promotionMapper.toResponseList(promotionRepository.findByPourcentagesInOrderByDateDebut(pourcentages));
    }

    @Override
    public List<PromotionResponce> findActivePromotionsOrderByPourcentage() {
        return promotionMapper.toResponseList(promotionRepository.findActivePromotionsOrderByPourcentage());
    }

    @Override
    public List<PromotionResponce> findByDateFinIsNull() {
        return promotionMapper.toResponseList(promotionRepository.findByDateFinIsNull());
    }

    @Override
    public List<PromotionResponce> findByPourcentageIsNotNull() {
        return promotionMapper.toResponseList(promotionRepository.findByPourcentageIsNotNull());
    }

    @Override
    public List<PromotionResponce> findAllWithArticles() {
        return promotionMapper.toResponseList(promotionRepository.findAllWithArticles());
    }

    @Override
    public List<PromotionResponce> findExpiredPromotions() {
        return promotionMapper.toResponseList(promotionRepository.findExpiredPromotions());
    }


    @Override
    public void ajouterPromoEtAffecterAArticle(PromotionRequest request, long idArticle) {
        Promotion promotion = promotionMapper.toEntity(request);
        Promotion savedPromotion = promotionRepository.save(promotion);
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        if (article.getPromotions() == null) {
            article.setPromotions(new ArrayList<>());
        }
        article.getPromotions().add(savedPromotion);
        articleRepository.save(article);
    }

}
