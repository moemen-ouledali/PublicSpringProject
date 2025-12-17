package tn.esprit.spring.tpcafeskanderbardaoui.services.Detail_Commande;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.DetailCommandeDTO.DetailCommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Detail_Commande;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IDetailCommandeMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.DetailCommandeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Detail_CommandeService implements IDetail_CommandeService {

    private final DetailCommandeRepository detailRepo;
    private final IDetailCommandeMapper detailMapper;

    // ================================
    //        CRUD METHODS
    // ================================
    @Override
    public DetailCommandeResponce addDetailCommande(DetailCommandeRequest request) {
        Detail_Commande detail = detailMapper.toEntity(request);
        Detail_Commande saved = detailRepo.save(detail);
        return detailMapper.toResponse(saved);
    }

    @Override
    public List<DetailCommandeResponce> saveDetailsCommande(List<DetailCommandeRequest> requests) {
        List<Detail_Commande> details = requests.stream()
                .map(detailMapper::toEntity)
                .collect(Collectors.toList());

        return detailRepo.saveAll(details).stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DetailCommandeResponce selectDetailCommandeById(long id) {
        Optional<Detail_Commande> detailOpt = detailRepo.findById(id);
        return detailOpt.map(detailMapper::toResponse).orElse(null);
    }

    @Override
    public List<DetailCommandeResponce> selectAllDetailsCommande() {
        return detailRepo.findAll().stream()
                .map(detailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetailCommandeById(long id) {
        if (detailRepo.existsById(id)) {
            detailRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAllDetailsCommande() {
        detailRepo.deleteAll();
    }

    @Override
    public long countingDetailsCommande() {
        return detailRepo.count();
    }

    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailRepo.existsById(id);
    }

    // ================================
    //        JPQL QUERY METHODS
    // ================================

    @Override
    public List<DetailCommandeResponce> findByQuantiteExact(Integer quantite) {
        return detailRepo.findByQuantiteArticle(quantite).stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findBySousTotalExact(Double sousTotal) {
        return detailRepo.findBySousTotalDetailArticle(sousTotal).stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public long countByQuantiteGreaterThan(Integer quantite) {
        return detailRepo.countByQuantiteArticleGreaterThan(quantite);
    }

    @Override
    public boolean existsBySousTotalGreaterThan(Double montant) {
        return detailRepo.existsBySousTotalDetailArticleGreaterThan(montant);
    }

    @Override
    public List<DetailCommandeResponce> findByQuantiteBetweenAndSousTotalMin(Integer minQte, Integer maxQte, Double minSousTotal) {
        return detailRepo.findByQuantiteBetweenAndSousTotalMin(minQte, maxQte, minSousTotal).stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findBySousTotalBetweenOrderByQuantite(Double minSousTotal, Double maxSousTotal) {
        return detailRepo.findBySousTotalBetweenOrderByQuantite(minSousTotal, maxSousTotal).stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findBySousTotalApresPromoBetween(Double min, Double max) {
        return detailRepo.findBySousTotalDetailArticleApresPromoBetween(min, max).stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findByQuantiteOrSousTotalMin(Integer quantite, Double minSousTotal) {
        return detailRepo.findByQuantiteOrSousTotalMin(quantite, minSousTotal).stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findTop5BySousTotalDesc() {
        return detailRepo.findTop5ByOrderBySousTotalDetailArticleDesc().stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findByQuantiteIsNull() {
        return detailRepo.findByQuantiteArticleIsNull().stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findBySousTotalApresPromoIsNotNull() {
        return detailRepo.findBySousTotalDetailArticleApresPromoIsNotNull().stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<DetailCommandeResponce> findAllWithCommandeAndArticle() {
        return detailRepo.findAllWithCommandeAndArticle().stream()
                .map(detailMapper::toResponse).collect(Collectors.toList());
    }
}
