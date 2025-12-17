package tn.esprit.spring.tpcafeskanderbardaoui.services.CarteFidelite;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CarteFideliteDTO.CarteFideliteResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.CarteFidelite;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.ICarteFideliteMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.CarteFideliteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarteFideliteService implements ICarteFideliteService {

    private final CarteFideliteRepository carteFideliteRepo;
    private final ICarteFideliteMapper carteFideliteMapper;

    // =============================
    //        CRUD METHODS
    // =============================

    @Override
    public CarteFideliteResponce addCarteFidelite(CarteFideliteRequest request) {
        CarteFidelite carteFidelite = carteFideliteMapper.toEntity(request);
        CarteFidelite saved = carteFideliteRepo.save(carteFidelite);
        return carteFideliteMapper.toResponse(saved);
    }

    @Override
    public List<CarteFideliteResponce> saveCarteFidelites(List<CarteFideliteRequest> requests) {
        List<CarteFidelite> carteFidelites = requests.stream()
                .map(carteFideliteMapper::toEntity)
                .collect(Collectors.toList());
        return carteFideliteRepo.saveAll(carteFidelites).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarteFideliteResponce selectCarteFideliteById(long id) {
        Optional<CarteFidelite> carteFidelite = carteFideliteRepo.findById(id);
        return carteFidelite.map(carteFideliteMapper::toResponse).orElse(null);
    }

    @Override
    public List<CarteFideliteResponce> selectAllCarteFidelites() {
        return carteFideliteRepo.findAll().stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCarteFideliteById(long id) {
        carteFideliteRepo.deleteById(id);
    }

    @Override
    public void deleteAllCarteFidelites() {
        carteFideliteRepo.deleteAll();
    }

    @Override
    public long countingCarteFidelites() {
        return carteFideliteRepo.count();
    }

    @Override
    public boolean verifCarteFideliteById(long id) {
        return carteFideliteRepo.existsById(id);
    }

    // =============================
    //    JPQL QUERY METHODS
    // =============================

    @Override
    public List<CarteFideliteResponce> findByPointsExact(Integer points) {
        return carteFideliteRepo.findByPointsExact(points).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findByDateCreation(LocalDate date) {
        return carteFideliteRepo.findByDateCreation(date).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countByPointsGreaterThan(Integer points) {
        return carteFideliteRepo.countByPointsGreaterThan(points);
    }

    @Override
    public int deleteByDateCreationBefore(LocalDate date) {
        return carteFideliteRepo.deleteByDateCreationBefore(date);
    }

    @Override
    public List<CarteFideliteResponce> findByPointsInRangeAndDateAfter(Integer minPoints, Integer maxPoints, LocalDate date) {
        return carteFideliteRepo.findByPointsInRangeAndDateAfter(minPoints, maxPoints, date).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findByPointsGreaterThanEqualOrderByDateCreation(Integer minPoints) {
        return carteFideliteRepo.findByPointsGreaterThanEqualOrderByDateCreation(minPoints).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findByDateCreationBetween(LocalDate startDate, LocalDate endDate) {
        return carteFideliteRepo.findByDateCreationBetween(startDate, endDate).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findByLowPointsOrDateBefore(Integer maxPoints, LocalDate date) {
        return carteFideliteRepo.findByLowPointsOrDateBefore(maxPoints, date).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarteFideliteResponce findCardWithMaxPoints() {
        Optional<CarteFidelite> carteFidelite = carteFideliteRepo.findCardWithMaxPoints();
        return carteFidelite.map(carteFideliteMapper::toResponse).orElse(null);
    }

    @Override
    public List<CarteFideliteResponce> findByDateCreationIsNull() {
        return carteFideliteRepo.findByDateCreationIsNull().stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findByPointsIsNotNull() {
        return carteFideliteRepo.findByPointsIsNotNull().stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findByClientNomAndPrenom(String nom, String prenom) {
        return carteFideliteRepo.findByClientNomAndPrenom(nom, prenom).stream()
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarteFideliteResponce> findTop5ByOrderByPointsDesc() {
        List<CarteFidelite> top5 = carteFideliteRepo.findTop5ByOrderByPointsDesc();
        return top5.stream()
                .limit(5)
                .map(carteFideliteMapper::toResponse)
                .collect(Collectors.toList());
    }
}