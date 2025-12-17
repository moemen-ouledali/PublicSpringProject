package tn.esprit.spring.tpcafeskanderbardaoui.services.Client;

import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;

import java.time.LocalDate;
import java.util.List;

public interface IClientService {

    // =============================
    //        CRUD METHODS
    // =============================
    ClientResponce addClient(ClientRequest request);

    List<ClientResponce> saveClients(List<ClientRequest> requests);

    ClientResponce selectClientById(long id);

    List<ClientResponce> selectAllClients();

    void deleteClientById(long id);

    void deleteAllClients();

    long countingClients();

    boolean verifClientById(long id);

    // =============================
    //        JPQL QUERY METHODS
    // =============================

    List<ClientResponce> findClientsByNom(String nom);

    List<ClientResponce> findClientsByPrenom(String prenom);

    // ✅ This should return List, NOT single ClientResponce
    List<ClientResponce> findClientsByNomAndPrenom(String nom, String prenom);

    boolean existsClientByNom(String nom);

    long countClientsByDateNaissanceAfter(LocalDate date);

    List<ClientResponce> findClientsByNomOrPrenomContaining(String str);

    List<ClientResponce> findClientsByNomAndPrenomContaining(String str);

    List<ClientResponce> findClientsByDateNaissanceBetween(LocalDate start, LocalDate end);

    List<ClientResponce> findClientsByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date);

    List<ClientResponce> findClientsByAdresseVille(String ville);

    List<ClientResponce> findClientsByNomContainingOrderByPrenomAsc(String str);

    List<ClientResponce> findClientsByNomContainingOrderByPrenomDesc(String str);

    List<ClientResponce> findClientsByNomStartingWith(String letter);

    List<ClientResponce> findClientsByPrenomEndingWith(String suffix);

    List<ClientResponce> findClientsByDateNaissanceIsNull();

    List<ClientResponce> findClientsByAdresseIsNotNull();

    List<ClientResponce> findClientsByAdresseVilleIn(List<String> villes);

    List<ClientResponce> findClientsByPtsAccumulesGreaterThan(int pts);

    List<ClientResponce> findClientsByPtsAccumulesGreaterThanOrEqual(int pts);

    List<ClientResponce> findClientsByPtsAccumulesBetween(int min, int max);

    List<ClientResponce> findClientsByCommandeArticleNom(String nomArticle);

    List<ClientResponce> findClientsByNomContainingAndArticleType(String nomStr, String typeArticle);

    // ===============================================
    //       AFFECTATION METHODS
    // ===============================================
    String affecterAdresseAClient(String rue, long idClient);

    void affecterCarteAClient(long idCarte, long idClient);

    void affecterCommandeAClient(long idCommande, long idClient);

    void affecterCommandeAClient(LocalDate dateCommande, String nomClient, String prenomClient);

    void desaffecterClientDeCommande(long idCommande);

    // ===============================================
    //            CASCADE METHODS
    // ===============================================
    ClientResponce ajouterClientEtCarteFidelite(ClientRequest request);

    void ajouteCommandeEtAffecterAClient(Commande commande, String nomClient, String prenomClient);

    void deleteClientAndCard(Long idClient);
    void incrementFidelityPointsOnBirthday();




}