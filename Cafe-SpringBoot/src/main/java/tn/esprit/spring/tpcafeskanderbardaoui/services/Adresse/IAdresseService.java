package tn.esprit.spring.tpcafeskanderbardaoui.services.Adresse;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Adresse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;

import java.util.List;
public interface IAdresseService {

    AdresseResponse addAdresse(AdresseRequest request);

    List<AdresseResponse> saveAdresses(List<AdresseRequest> requests);

    AdresseResponse selectAdresseById(long id);

    List<AdresseResponse> selectAllAdresses();

    void deleteAdresse(long id);

    void deleteAllAdresses();

    long countingAdresses();

    boolean verifAdresseById(long id);


    // ======================
    //      JPQL QUERIES
    // ======================

    List<AdresseResponse> findByVille(String ville);

    List<AdresseResponse> findByCodePostal(int codePostal);

    long countByVille(String ville);

    void deleteByVille(String ville);

    List<AdresseResponse> findByVilleAndCodePostal(String ville, int codePostal);

    List<AdresseResponse> findByRueContainingIgnoreCase(String word);

    List<AdresseResponse> findByVilleIn(List<String> villes);

    List<AdresseResponse> findByCodePostalBetween(int min, int max);

    List<AdresseResponse> findByCodePostalGreaterThan(int cp);

    List<AdresseResponse> findByCodePostalGreaterThanOrEqual(int cp);

    List<AdresseResponse> findByCodePostalLessThan(int cp);

    List<AdresseResponse> findByCodePostalLessThanOrEqual(int cp);

    List<AdresseResponse> findByRueStartingWithAndVilleOrderByCodePostal(String prefix, String ville);

    List<AdresseResponse> findByRueStartingWith(String prefix);

    List<AdresseResponse> findByVilleEndingWith(String suffix);

    List<AdresseResponse> findByRueIsNull();

    List<AdresseResponse> findByVilleIsNotNull();

    Client ajouterClientEtAdresse(Client client, Adresse adresse);

}
