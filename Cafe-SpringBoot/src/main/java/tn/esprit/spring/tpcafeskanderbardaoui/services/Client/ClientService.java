package tn.esprit.spring.tpcafeskanderbardaoui.services.Client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.ClientDTO.ClientResponce;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Adresse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Article;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.CarteFidelite;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Commande;
import tn.esprit.spring.tpcafeskanderbardaoui.mapper.IClientMapper;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.AdresseRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.CarteFideliteRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.ClientRepository;
import tn.esprit.spring.tpcafeskanderbardaoui.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClientService implements IClientService {

    private final ClientRepository clientRepo;
    private final IClientMapper clientMapper;
    private final AdresseRepository adresseRepository;
    private final CommandeRepository commandeRepository;
    private final CarteFideliteRepository carteRepo;

    // =============================
    //        CRUD METHODS
    // =============================

    @Override
    public ClientResponce addClient(ClientRequest request) {
        Client client = clientMapper.toEntity(request);
        Client saved = clientRepo.save(client);
        return clientMapper.toResponse(saved);
    }

    @Override
    public List<ClientResponce> saveClients(List<ClientRequest> requests) {
        List<Client> clients = requests.stream()
                .map(clientMapper::toEntity)
                .collect(Collectors.toList());
        return clientRepo.saveAll(clients).stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponce selectClientById(long id) {
        return clientRepo.findById(id)
                .map(clientMapper::toResponse)
                .orElse(null);
    }

    @Override
    public List<ClientResponce> selectAllClients() {
        return clientRepo.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientById(long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepo.deleteAll();
    }

    @Override
    public long countingClients() {
        return clientRepo.count();
    }

    @Override
    public boolean verifClientById(long id) {
        return clientRepo.existsById(id);
    }

    // =============================
    //    JPQL QUERY METHODS
    // =============================

    @Override
    public List<ClientResponce> findClientsByNom(String nom) {
        return clientRepo.findByNom(nom)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByPrenom(String prenom) {
        return clientRepo.findByPrenom(prenom)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ClientResponce> findClientsByNomAndPrenom(String nom, String prenom) {
        List<Client> clients = clientRepo.findAllByNomAndPrenom(nom, prenom);
        return clients.stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsClientByNom(String nom) {
        return clientRepo.existsByNom(nom);
    }

    @Override
    public long countClientsByDateNaissanceAfter(LocalDate date) {
        return clientRepo.countByDateNaissanceAfter(date);
    }

    @Override
    public List<ClientResponce> findClientsByNomOrPrenomContaining(String str) {
        return clientRepo.findByNomOrPrenomContaining(str)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByNomAndPrenomContaining(String str) {
        return clientRepo.findByNomAndPrenomContaining(str)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByDateNaissanceBetween(LocalDate start, LocalDate end) {
        return clientRepo.findByDateNaissanceBetween(start, end)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date) {
        return clientRepo.findByNomStartingWithAndDateNaissanceBefore(prefix, date)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByAdresseVille(String ville) {
        return clientRepo.findByAdresseVille(ville)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByNomContainingOrderByPrenomAsc(String str) {
        return clientRepo.findByNomContainingOrderByPrenomAsc(str)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByNomContainingOrderByPrenomDesc(String str) {
        return clientRepo.findByNomContainingOrderByPrenomDesc(str)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByNomStartingWith(String letter) {
        return clientRepo.findByNomStartingWith(letter)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByPrenomEndingWith(String suffix) {
        return clientRepo.findByPrenomEndingWith(suffix)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByDateNaissanceIsNull() {
        return clientRepo.findByDateNaissanceIsNull()
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByAdresseIsNotNull() {
        return clientRepo.findByAdresseIsNotNull()
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByAdresseVilleIn(List<String> villes) {
        return clientRepo.findByAdresseVilleIn(villes)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByPtsAccumulesGreaterThan(int pts) {
        return clientRepo.findByPtsAccumulesGreaterThan(pts)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByPtsAccumulesGreaterThanOrEqual(int pts) {
        return clientRepo.findByPtsAccumulesGreaterThanOrEqual(pts)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByPtsAccumulesBetween(int min, int max) {
        return clientRepo.findByPtsAccumulesBetween(min, max)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByCommandeArticleNom(String nomArticle) {
        return clientRepo.findByCommandeArticleNom(nomArticle)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponce> findClientsByNomContainingAndArticleType(String nomStr, String typeArticle) {
        return clientRepo.findByNomContainingAndArticleType(nomStr, typeArticle)
                .stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    // =============================
    //  AFFECTATION METHODS
    // =============================

    @Override
    public String affecterAdresseAClient(String rue, long idClient) {
        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        List<Adresse> adresses = adresseRepository.findByRueContainingIgnoreCase(rue);

        if (adresses.isEmpty()) {
            throw new RuntimeException("Aucune adresse trouvée pour la rue: " + rue);
        }

        if (adresses.size() > 1) {
            throw new RuntimeException("Plusieurs adresses trouvées pour '" + rue +
                    "'. Veuillez être plus précis ou utiliser l'ID de l'adresse.");
        }

        Adresse adresse = adresses.get(0);
        client.setAdresse(adresse);
        clientRepo.save(client);
        return "Adresse affectée au client : " + client.getNom();
    }

    @Override
    public void affecterCommandeAClient(long idCommande, long idClient) {
        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande not found"));

        commande.setClient(client);
        commandeRepository.save(commande);
    }

    @Override
    public void affecterCommandeAClient(LocalDate dateCommande, String nom, String prenom) {
        List<Client> clients = clientRepo.findAllByNomAndPrenom(nom, prenom);

        if (clients.isEmpty()) {
            throw new RuntimeException("Client not found with nom: " + nom + " and prenom: " + prenom);
        }

        if (clients.size() > 1) {
            throw new RuntimeException("Plusieurs clients trouvés (" + clients.size() +
                    ") avec le nom: " + nom + " " + prenom +
                    ". Veuillez utiliser l'endpoint avec l'ID du client pour éviter toute ambiguïté.");
        }

        Client client = clients.get(0);

        List<Commande> commandes = commandeRepository.findByDateExacte(dateCommande);
        if (commandes.isEmpty()) {
            throw new RuntimeException("Aucune commande trouvée à cette date: " + dateCommande);
        }

        for (Commande commande : commandes) {
            commande.setClient(client);
            commandeRepository.save(commande);
        }
    }

    @Override
    public void desaffecterClientDeCommande(long idCommande) {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        commande.setClient(null);
        commandeRepository.save(commande);
    }

    @Override
    public void affecterCarteAClient(long idCarte, long idClient) {
        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CarteFidelite carte = carteRepo.findById(idCarte)
                .orElseThrow(() -> new RuntimeException("Carte non trouvée"));

        // Set both sides of the bidirectional relationship
        carte.setClient(client);  // ✅ This is the owning side (has the FK)
        client.setCarteFidelite(carte);  // ✅ This maintains consistency

        // Save the owning side (CarteFidelite has the foreign key)
        carteRepo.save(carte);
    }

    // =============================
    //  CASCADE METHODS
    // =============================

    @Override
    public ClientResponce ajouterClientEtCarteFidelite(ClientRequest request) {

        // 1️⃣ Convert and save the client
        Client client = clientMapper.toEntity(request);
        Client savedClient = clientRepo.save(client);

        // 2️⃣ Map request card into entity
        CarteFidelite card = new CarteFidelite();
        card.setPointsAcumules(request.getCarte().getPointsAcumules());  // ✅ take value from request
        card.setDateCreation(request.getCarte().getDateCreation());      // ✅ take value from request
        card.setClient(savedClient);  // link to client

        // 3️⃣ Save the card
        carteRepo.save(card);

        // 4️⃣ Set it back to client if you need cascade link
        savedClient.setCarteFidelite(card);

        // 5️⃣ Return response DTO
        return clientMapper.toResponse(savedClient);
    }



    @Override
    public void ajouteCommandeEtAffecterAClient(Commande commande, String nomClient, String prenomClient) {
        // 1️⃣ Find the client by nom and prenom
        List<Client> clients = clientRepo.findAllByNomAndPrenom(nomClient, prenomClient);
        if (clients.isEmpty()) {
            throw new RuntimeException("Client not found with nom: " + nomClient + " and prenom: " + prenomClient);
        }
        Client client = clients.get(0);
        // 2️⃣ Attach the commande to the client
        commande.setClient(client);
        // 3️⃣ Save the commande
        commandeRepository.save(commande);
    }

    @Transactional
    @Override
    public void deleteClientAndCard(Long idClient) {

        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        CarteFidelite carte = client.getCarteFidelite();

        if (carte != null) {
            carte.setClient(null);
            client.setCarteFidelite(null);
            carteRepo.delete(carte); // ✅ delete card with correct repo
        }

        clientRepo.delete(client); // ✅ delete client with client repo
    }



    // Runs every day at midnight
    @Override
    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void incrementFidelityPointsOnBirthday() {

        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        // Only fetch clients whose birthday is today
        List<Client> birthdayClients = clientRepo.findClientsByBirthday(month, day);

        for (Client client : birthdayClients) {
            CarteFidelite carte = client.getCarteFidelite();
            if (carte == null) {
                log.warn("Client {} has no loyalty card, skipping.", client.getNom());
                continue;
            }

            int oldPoints = carte.getPointsAcumules();
            int bonus = (int) (oldPoints * 0.10); // +10%
            carte.setPointsAcumules(oldPoints + bonus);
            carteRepo.save(carte);

            log.info("🎉 Birthday bonus applied to client {} {} → Old: {}, New: {}",
                    client.getNom(), client.getPrenom(), oldPoints, oldPoints + bonus);
        }

        log.info("Birthday loyalty points update completed. {} clients processed.", birthdayClients.size());
    }


}