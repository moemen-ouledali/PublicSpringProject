package tn.esprit.spring.tpcafeskanderbardaoui.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseRequest;
import tn.esprit.spring.tpcafeskanderbardaoui.dto.AdresseDTO.AdresseResponse;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;
import tn.esprit.spring.tpcafeskanderbardaoui.services.Adresse.IAdresseService;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adresse")
public class AdresseRestController {

    private final IAdresseService adresseService;

    // =============================
    //       CRUD ENDPOINTS
    // =============================

    @GetMapping
    public List<AdresseResponse> selectAllAdresses() {
        return adresseService.selectAllAdresses();
    }

    @GetMapping("/{id}")
    public AdresseResponse selectAdresseById(@PathVariable long id) {
        return adresseService.selectAdresseById(id);
    }

    @PostMapping
    public AdresseResponse addAdresse(@RequestBody AdresseRequest adresseRequest) {
        return adresseService.addAdresse(adresseRequest);
    }

    @PostMapping("/batch")
    public List<AdresseResponse> saveAdresses(@RequestBody List<AdresseRequest> adresseRequests) {
        return adresseService.saveAdresses(adresseRequests);
    }

    @DeleteMapping("/{id}")
    public void deleteAdresseById(@PathVariable long id) {
        adresseService.deleteAdresse(id);
    }

    @DeleteMapping
    public void deleteAllAdresses() {
        adresseService.deleteAllAdresses();
    }

    @GetMapping("/count")
    public long countAdresses() {
        return adresseService.countingAdresses();
    }

    @GetMapping("/exists/{id}")
    public boolean verifAdresseById(@PathVariable long id) {
        return adresseService.verifAdresseById(id);
    }


    // =============================
    //      JPQL QUERY ENDPOINTS
    // =============================

    @GetMapping("/ville/{ville}")
    public List<AdresseResponse> findByVille(@PathVariable String ville) {
        return adresseService.findByVille(ville);
    }

    @GetMapping("/code-postal/{cp}")
    public List<AdresseResponse> findByCodePostal(@PathVariable int cp) {
        return adresseService.findByCodePostal(cp);
    }

    @GetMapping("/ville/{ville}/count")
    public long countByVille(@PathVariable String ville) {
        return adresseService.countByVille(ville);
    }

    @DeleteMapping("/ville/{ville}")
    public void deleteByVille(@PathVariable String ville) {
        adresseService.deleteByVille(ville);
    }

    @GetMapping("/ville/{ville}/code-postal/{cp}")
    public List<AdresseResponse> findByVilleAndCodePostal(@PathVariable String ville, @PathVariable int cp) {
        return adresseService.findByVilleAndCodePostal(ville, cp);
    }

    @GetMapping("/rue/contains/{word}")
    public List<AdresseResponse> findByRueContainingIgnoreCase(@PathVariable String word) {
        return adresseService.findByRueContainingIgnoreCase(word);
    }

    @PostMapping("/ville/in")
    public List<AdresseResponse> findByVilleIn(@RequestBody List<String> villes) {
        return adresseService.findByVilleIn(villes);
    }

    @GetMapping("/code-postal/between")
    public List<AdresseResponse> findByCodePostalBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return adresseService.findByCodePostalBetween(min, max);
    }

    @GetMapping("/code-postal/greater/{cp}")
    public List<AdresseResponse> findByCodePostalGreaterThan(@PathVariable int cp) {
        return adresseService.findByCodePostalGreaterThan(cp);
    }

    @GetMapping("/code-postal/greater-equal/{cp}")
    public List<AdresseResponse> findByCodePostalGreaterThanOrEqual(@PathVariable int cp) {
        return adresseService.findByCodePostalGreaterThanOrEqual(cp);
    }

    @GetMapping("/code-postal/less/{cp}")
    public List<AdresseResponse> findByCodePostalLessThan(@PathVariable int cp) {
        return adresseService.findByCodePostalLessThan(cp);
    }

    @GetMapping("/code-postal/less-equal/{cp}")
    public List<AdresseResponse> findByCodePostalLessThanOrEqual(@PathVariable int cp) {
        return adresseService.findByCodePostalLessThanOrEqual(cp);
    }

    @GetMapping("/ville/{ville}/rue/starts/{prefix}")
    public List<AdresseResponse> findByRueStartingWithAndVilleOrderByCodePostal(
            @PathVariable String prefix,
            @PathVariable String ville) {
        return adresseService.findByRueStartingWithAndVilleOrderByCodePostal(prefix, ville);
    }

    @GetMapping("/rue/starts/{prefix}")
    public List<AdresseResponse> findByRueStartingWith(@PathVariable String prefix) {
        return adresseService.findByRueStartingWith(prefix);
    }

    @GetMapping("/ville/ends/{suffix}")
    public List<AdresseResponse> findByVilleEndingWith(@PathVariable String suffix) {
        return adresseService.findByVilleEndingWith(suffix);
    }

    @GetMapping("/rue/is-null")
    public List<AdresseResponse> findByRueIsNull() {
        return adresseService.findByRueIsNull();
    }

    @GetMapping("/ville/is-not-null")
    public List<AdresseResponse> findByVilleIsNotNull() {
        return adresseService.findByVilleIsNotNull();
    }

    @PostMapping("/add-client-with-adresse")
    public ResponseEntity<Client> addClientWithAdresse(@RequestBody Client client) {
        Client savedClient = adresseService.ajouterClientEtAdresse(
                client, client.getAdresse());
        return ResponseEntity.ok(savedClient);
    }

}



