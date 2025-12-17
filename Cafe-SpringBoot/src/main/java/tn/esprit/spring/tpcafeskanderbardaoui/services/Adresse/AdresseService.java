package tn.esprit.spring.tpcafeskanderbardaoui.services.Adresse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Adresse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IAdresseMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.AdresseRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdresseService implements IAdresseService {

    private final AdresseRepository adresseRepo;
    private final IAdresseMapper adresseMapper;
    private final ClientRepository clientRepo;

    @Override
    public AdresseResponse addAdresse(AdresseRequest request) {
        Adresse adresse = adresseMapper.toEntity(request);
        Adresse saved = adresseRepo.save(adresse);
        return adresseMapper.toResponse(saved);
    }

    @Override
    public List<AdresseResponse> saveAdresses(List<AdresseRequest> requests) {
        List<Adresse> adresses = requests.stream()
                .map(adresseMapper::toEntity)
                .collect(Collectors.toList());

        List<Adresse> saved = adresseRepo.saveAll(adresses);
        return saved.stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdresseResponse selectAdresseById(long id) {
        return adresseRepo.findById(id)
                .map(adresseMapper::toResponse)
                .orElseGet(() -> AdresseResponse.builder()
                        .idAdresse(null)
                        .rue("default rue")
                        .ville("default ville")
                        .build());
    }

    @Override
    public List<AdresseResponse> selectAllAdresses() {
        return adresseRepo.findAll().stream()
                .map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdresse(long id) {
        adresseRepo.deleteById(id);
    }

    @Override
    public void deleteAllAdresses() {
        adresseRepo.deleteAll();
    }

    @Override
    public long countingAdresses() {
        return adresseRepo.count();
    }

    @Override
    public boolean verifAdresseById(long id) {
        return adresseRepo.existsById(id);
    }

    // =======================
//    JPQL QUERY METHODS
// =======================

    @Override
    public List<AdresseResponse> findByVille(String ville) {
        return adresseRepo.findByVille(ville)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByCodePostal(int codePostal) {
        return adresseRepo.findByCodePostal(codePostal)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long countByVille(String ville) {
        return adresseRepo.countByVille(ville);
    }

    @Override
    public void deleteByVille(String ville) {
        adresseRepo.deleteByVille(ville);
    }

    @Override
    public List<AdresseResponse> findByVilleAndCodePostal(String ville, int codePostal) {
        return adresseRepo.findByVilleAndCodePostal(ville, codePostal)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByRueContainingIgnoreCase(String word) {
        return adresseRepo.findByRueContainingIgnoreCase(word)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByVilleIn(List<String> villes) {
        return adresseRepo.findByVilleIn(villes)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByCodePostalBetween(int min, int max) {
        return adresseRepo.findByCodePostalBetween(min, max)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByCodePostalGreaterThan(int cp) {
        return adresseRepo.findByCodePostalGreaterThan(cp)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByCodePostalGreaterThanOrEqual(int cp) {
        return adresseRepo.findByCodePostalGreaterThanOrEqual(cp)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByCodePostalLessThan(int cp) {
        return adresseRepo.findByCodePostalLessThan(cp)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByCodePostalLessThanOrEqual(int cp) {
        return adresseRepo.findByCodePostalLessThanOrEqual(cp)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByRueStartingWithAndVilleOrderByCodePostal(String prefix, String ville) {
        return adresseRepo.findByRueStartingWithAndVilleOrderByCodePostal(prefix, ville)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByRueStartingWith(String prefix) {
        return adresseRepo.findByRueStartingWith(prefix)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByVilleEndingWith(String suffix) {
        return adresseRepo.findByVilleEndingWith(suffix)
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByRueIsNull() {
        return adresseRepo.findByRueIsNull()
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdresseResponse> findByVilleIsNotNull() {
        return adresseRepo.findByVilleIsNotNull()
                .stream().map(adresseMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Client ajouterClientEtAdresse(Client client, Adresse adresse) {
        // Associer l'adresse au client
        client.setAdresse(adresse);

        // Optionnel si relation bidirectionnelle
        adresse.setClients(client);

        // Sauvegarder le client (l'adresse sera sauvegardée automatiquement)
        return clientRepo.save(client);
    }



}
