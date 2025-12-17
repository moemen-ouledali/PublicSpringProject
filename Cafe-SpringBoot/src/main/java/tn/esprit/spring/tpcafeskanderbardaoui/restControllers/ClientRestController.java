package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;
import tn.esprit.spring.tpcafeskanderbardaoui.services.Client.IClientService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientRestController {

    private final IClientService clientService;

    // =============================
    //        CRUD ENDPOINTS
    // =============================

    @GetMapping
    public List<ClientResponce> selectAllClients() {
        return clientService.selectAllClients();
    }

    @GetMapping("/{id}")
    public ClientResponce selectClientById(@PathVariable long id) {
        return clientService.selectClientById(id);
    }

    @PostMapping
    public ClientResponce addClient(@RequestBody ClientRequest request) {
        return clientService.addClient(request);
    }

    @PostMapping("/batch")
    public List<ClientResponce> saveClients(@RequestBody List<ClientRequest> requests) {
        return clientService.saveClients(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable long id) {
        clientService.deleteClientById(id);
    }

    @DeleteMapping
    public void deleteAllClients() {
        clientService.deleteAllClients();
    }

    @GetMapping("/count")
    public long countClients() {
        return clientService.countingClients();
    }

    @GetMapping("/exists/{id}")
    public boolean verifClientById(@PathVariable long id) {
        return clientService.verifClientById(id);
    }

    // =============================
    //    JPQL QUERY ENDPOINTS
    // =============================

    @GetMapping("/search/nom")
    public List<ClientResponce> findByNom(@RequestParam String nom) {
        return clientService.findClientsByNom(nom);
    }

    @GetMapping("/search/prenom")
    public List<ClientResponce> findByPrenom(@RequestParam String prenom) {
        return clientService.findClientsByPrenom(prenom);
    }

    // ✅ FIXED: Now returns List instead of single client
    @GetMapping("/search/nom-prenom")
    public ResponseEntity<List<ClientResponce>> findByNomAndPrenom(
            @RequestParam String nom,
            @RequestParam String prenom) {
        List<ClientResponce> clients = clientService.findClientsByNomAndPrenom(nom, prenom);
        return clients.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(clients);
    }

    @GetMapping("/exists/nom")
    public boolean existsByNom(@RequestParam String nom) {
        return clientService.existsClientByNom(nom);
    }

    @GetMapping("/count/born-after")
    public long countByDateNaissanceAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return clientService.countClientsByDateNaissanceAfter(date);
    }

    @GetMapping("/search/nom-or-prenom-containing")
    public List<ClientResponce> findByNomOrPrenomContaining(@RequestParam String str) {
        return clientService.findClientsByNomOrPrenomContaining(str);
    }

    @GetMapping("/search/nom-and-prenom-containing")
    public List<ClientResponce> findByNomAndPrenomContaining(@RequestParam String str) {
        return clientService.findClientsByNomAndPrenomContaining(str);
    }

    @GetMapping("/search/born-between")
    public List<ClientResponce> findByDateNaissanceBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return clientService.findClientsByDateNaissanceBetween(start, end);
    }

    @GetMapping("/search/nom-starts-born-before")
    public List<ClientResponce> findByNomStartingWithAndDateNaissanceBefore(
            @RequestParam String prefix,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return clientService.findClientsByNomStartingWithAndDateNaissanceBefore(prefix, date);
    }

    @GetMapping("/search/ville")
    public List<ClientResponce> findByAdresseVille(@RequestParam String ville) {
        return clientService.findClientsByAdresseVille(ville);
    }

    @GetMapping("/search/nom-containing-sort-asc")
    public List<ClientResponce> findByNomContainingOrderByPrenomAsc(@RequestParam String str) {
        return clientService.findClientsByNomContainingOrderByPrenomAsc(str);
    }

    @GetMapping("/search/nom-containing-sort-desc")
    public List<ClientResponce> findByNomContainingOrderByPrenomDesc(@RequestParam String str) {
        return clientService.findClientsByNomContainingOrderByPrenomDesc(str);
    }

    @GetMapping("/search/nom-starts-with")
    public List<ClientResponce> findByNomStartingWith(@RequestParam String letter) {
        return clientService.findClientsByNomStartingWith(letter);
    }

    @GetMapping("/search/prenom-ends-with")
    public List<ClientResponce> findByPrenomEndingWith(@RequestParam String suffix) {
        return clientService.findClientsByPrenomEndingWith(suffix);
    }

    @GetMapping("/search/no-birthdate")
    public List<ClientResponce> findByDateNaissanceIsNull() {
        return clientService.findClientsByDateNaissanceIsNull();
    }

    @GetMapping("/search/with-address")
    public List<ClientResponce> findByAdresseIsNotNull() {
        return clientService.findClientsByAdresseIsNotNull();
    }

    @GetMapping("/search/villes")
    public List<ClientResponce> findByAdresseVilleIn(@RequestParam List<String> villes) {
        return clientService.findClientsByAdresseVilleIn(villes);
    }

    @GetMapping("/search/points-greater-than")
    public List<ClientResponce> findByPtsAccumulesGreaterThan(@RequestParam int pts) {
        return clientService.findClientsByPtsAccumulesGreaterThan(pts);
    }

    @GetMapping("/search/points-greater-equal")
    public List<ClientResponce> findByPtsAccumulesGreaterThanOrEqual(@RequestParam int pts) {
        return clientService.findClientsByPtsAccumulesGreaterThanOrEqual(pts);
    }

    @GetMapping("/search/points-between")
    public List<ClientResponce> findByPtsAccumulesBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return clientService.findClientsByPtsAccumulesBetween(min, max);
    }

    @GetMapping("/search/by-article")
    public List<ClientResponce> findByCommandeArticleNom(@RequestParam String nomArticle) {
        return clientService.findClientsByCommandeArticleNom(nomArticle);
    }

    @GetMapping("/search/nom-and-article-type")
    public List<ClientResponce> findByNomContainingAndArticleType(
            @RequestParam String nomStr,
            @RequestParam String typeArticle) {
        return clientService.findClientsByNomContainingAndArticleType(nomStr, typeArticle);
    }

    // =============================
    //    CASCADE ENDPOINT
    // =============================

    @PostMapping("/add-with-carte")
    public ClientResponce addClientWithCarte(@RequestBody ClientRequest request) {
        return clientService.ajouterClientEtCarteFidelite(request);
    }

    // =============================
    //    AFFECTATION ENDPOINTS
    // =============================

    @PostMapping("/{idClient}/affecter-adresse")
    public ResponseEntity<String> affecterAdresse(
            @PathVariable long idClient,
            @RequestParam String rue) {
        try {
            String result = clientService.affecterAdresseAClient(rue, idClient);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{idClient}/affecter-commande")
    public ResponseEntity<String> affecterCommande(
            @PathVariable long idClient,
            @RequestParam long idCommande) {
        try {
            clientService.affecterCommandeAClient(idCommande, idClient);
            return ResponseEntity.ok("Commande affectée au client avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/affecter-commande-by-date")
    public ResponseEntity<String> affecterCommandeByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCommande,
            @RequestParam String nom,
            @RequestParam String prenom) {
        try {
            clientService.affecterCommandeAClient(dateCommande, nom, prenom);
            return ResponseEntity.ok("Commandes du " + dateCommande + " affectées au client " + nom + " " + prenom);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/desaffecter-commande/{idCommande}")
    public ResponseEntity<String> desaffecterCommande(@PathVariable long idCommande) {
        try {
            clientService.desaffecterClientDeCommande(idCommande);
            return ResponseEntity.ok("Commande désaffectée avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{idClient}/affecter-carte/{idCarte}")
    public ResponseEntity<String> affecterCarte(
            @PathVariable long idClient,
            @PathVariable long idCarte) {
        try {
            clientService.affecterCarteAClient(idCarte, idClient);
            return ResponseEntity.ok("Carte fidélité affectée au client avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-and-affect-commande")
    public ResponseEntity<String> ajouteCommandeEtAffecterAClient(
            @RequestBody tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande commande,
            @RequestParam String nomClient,
            @RequestParam String prenomClient) {
        try {
            clientService.ajouteCommandeEtAffecterAClient(commande, nomClient, prenomClient);
            return ResponseEntity.ok("Commande ajoutée et affectée au client " + nomClient + " " + prenomClient + " avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/delete-client-with-carte/{idClient}")
    public ResponseEntity<String> deleteClientWithCarte(@PathVariable Long idClient) {

        clientService.deleteClientAndCard(idClient);

        return ResponseEntity.ok("Client et carte fidélité supprimés avec succès");
    }

    @GetMapping("/test/birthday")
    public String testBirthdayPoints() {
        clientService.incrementFidelityPointsOnBirthday();
        return "Birthday points increment executed manually!";
    }

}