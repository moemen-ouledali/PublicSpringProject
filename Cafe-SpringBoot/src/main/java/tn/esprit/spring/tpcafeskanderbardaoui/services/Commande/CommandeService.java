package tn.esprit.spring.tpcafeskanderbardaoui.services.Commande;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.CommandeDTO.CommandeResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.StatusCommande;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.ICommandeMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeService implements ICommandeService {

    private final CommandeRepository commandeRepo;
    private final ICommandeMapper commandeMapper;

    // =============================
    //        CRUD METHODS
    // =============================

    @Override
    public CommandeResponce addCommande(CommandeRequest request) {
        Commande commande = commandeMapper.toEntity(request);
        Commande saved = commandeRepo.save(commande);
        return commandeMapper.toResponse(saved);
    }

    @Override
    public List<CommandeResponce> saveCommandes(List<CommandeRequest> requests) {
        List<Commande> commandes = requests.stream()
                .map(commandeMapper::toEntity)
                .collect(Collectors.toList());
        return commandeRepo.saveAll(commandes).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeResponce selectCommandeById(long id) {
        Optional<Commande> commande = commandeRepo.findById(id);
        return commande.map(commandeMapper::toResponse).orElse(null);
    }

    @Override
    public List<CommandeResponce> selectAllCommandes() {
        return commandeRepo.findAll().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommandeById(long id) {
        commandeRepo.deleteById(id);
    }

    @Override
    public void deleteAllCommandes() {
        commandeRepo.deleteAll();
    }

    @Override
    public long countingCommandes() {
        return commandeRepo.count();
    }

    @Override
    public boolean verifCommandeById(long id) {
        return commandeRepo.existsById(id);
    }

    // =============================
    //    JPQL QUERY METHODS
    // =============================

    @Override
    public List<CommandeResponce> findByStatut(StatusCommande statut) {
        return commandeRepo.findByStatut(statut).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByDateExacte(LocalDate date) {
        return commandeRepo.findByDateExacte(date).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countByStatut(StatusCommande statut) {
        return commandeRepo.countByStatut(statut);
    }

    @Override
    public int deleteByDateBefore(LocalDate date) {
        return commandeRepo.deleteByDateBefore(date);
    }

    @Override
    public List<CommandeResponce> findByDateBetweenAndStatut(LocalDate startDate, LocalDate endDate, StatusCommande statut) {
        return commandeRepo.findByDateBetweenAndStatut(startDate, endDate, statut).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByTotalGreaterThanAndStatutNot(Double montant, StatusCommande statut) {
        return commandeRepo.findByTotalGreaterThanAndStatutNot(montant, statut).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByStatutsInOrderByDate(List<StatusCommande> statuts) {
        return commandeRepo.findByStatutsInOrderByDate(statuts).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByDateBeforeAndTotalBetween(LocalDate date, Double minTotal, Double maxTotal) {
        return commandeRepo.findByDateBeforeAndTotalBetween(date, minTotal, maxTotal).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByStatutEndingWith(String suffix) {
        return commandeRepo.findByStatutEndingWith(suffix).stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByStatutIsNull() {
        return commandeRepo.findByStatutIsNull().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findByTotalIsNotNull() {
        return commandeRepo.findByTotalIsNotNull().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findAllWithDetailsAndClient() {
        return commandeRepo.findAllWithDetailsAndClient().stream()
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeResponce> findTop3RecentCommandes() {
        List<Commande> commandes = commandeRepo.findTop3RecentCommandes();
        return commandes.stream()
                .limit(3)
                .map(commandeMapper::toResponse)
                .collect(Collectors.toList());
    }
}