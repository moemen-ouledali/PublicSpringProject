package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Client;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // 1. Trouver tous les clients par nom de famille
    @Query("SELECT c FROM Client c WHERE c.nom = :nom")
    List<Client> findByNom(@Param("nom") String nom);

    // 2. Trouver les clients par prénom
    @Query("SELECT c FROM Client c WHERE c.prenom = :prenom")
    List<Client> findByPrenom(@Param("prenom") String prenom);

    // 3. Trouver un client spécifique par nom et prénom
    @Query("SELECT c FROM Client c WHERE c.nom = :nom AND c.prenom = :prenom")
    Client findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    // 4. Vérifier si un client existe par son nom
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.nom = :nom")
    boolean existsByNom(@Param("nom") String nom);

    // 5. Compter les clients nés après une certaine date
    @Query("SELECT COUNT(c) FROM Client c WHERE c.dateNaissance > :date")
    long countByDateNaissanceAfter(@Param("date") LocalDate date);

    // 6. Trouver les clients dont le nom ou le prénom contient une chaîne
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :str, '%')) " +
            "OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :str, '%'))")
    List<Client> findByNomOrPrenomContaining(@Param("str") String str);

    // 7. Trouver les clients dont le nom et le prénom contient une chaîne
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :str, '%')) " +
            "AND LOWER(c.prenom) LIKE LOWER(CONCAT('%', :str, '%'))")
    List<Client> findByNomAndPrenomContaining(@Param("str") String str);

    // 8. Trouver les clients nés entre deux dates
    @Query("SELECT c FROM Client c WHERE c.dateNaissance BETWEEN :start AND :end")
    List<Client> findByDateNaissanceBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // 9. Trouver les clients dont le nom commence par et nés avant une date
    @Query("SELECT c FROM Client c WHERE c.nom LIKE CONCAT(:prefix, '%') AND c.dateNaissance < :date")
    List<Client> findByNomStartingWithAndDateNaissanceBefore(@Param("prefix") String prefix, @Param("date") LocalDate date);

    // 10. Trouver les clients par ville de leur adresse (jointure implicite)
    @Query("SELECT c FROM Client c JOIN c.adresse a WHERE a.ville = :ville")
    List<Client> findByAdresseVille(@Param("ville") String ville);

    // 11. Clients par nom contient une chaine triés par prénom ASC
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :str, '%')) ORDER BY c.prenom ASC")
    List<Client> findByNomContainingOrderByPrenomAsc(@Param("str") String str);

    // 12. Clients par nom contient une chaine triés par prénom DESC
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :str, '%')) ORDER BY c.prenom DESC")
    List<Client> findByNomContainingOrderByPrenomDesc(@Param("str") String str);

    // 13. Clients dont le nom commence par une lettre spécifique
    @Query("SELECT c FROM Client c WHERE c.nom LIKE CONCAT(:letter, '%')")
    List<Client> findByNomStartingWith(@Param("letter") String letter);

    // 14. Clients dont le prénom se termine par une terminaison
    @Query("SELECT c FROM Client c WHERE c.prenom LIKE CONCAT('%', :suffix)")
    List<Client> findByPrenomEndingWith(@Param("suffix") String suffix);

    // 15. Clients sans date de naissance renseignée
    @Query("SELECT c FROM Client c WHERE c.dateNaissance IS NULL")
    List<Client> findByDateNaissanceIsNull();

    // 16. Clients avec une adresse renseignée
    @Query("SELECT c FROM Client c WHERE c.adresse IS NOT NULL")
    List<Client> findByAdresseIsNotNull();

    // 17. Clients des plusieurs villes
    @Query("SELECT c FROM Client c JOIN c.adresse a WHERE a.ville IN :villes")
    List<Client> findByAdresseVilleIn(@Param("villes") List<String> villes);

    // 18. Clients dont les ptsAccumules > valeur
    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAcumules > :pts")
    List<Client> findByPtsAccumulesGreaterThan(@Param("pts") int pts);

    // 19. Clients dont les ptsAccumules >= valeur
    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAcumules >= :pts")
    List<Client> findByPtsAccumulesGreaterThanOrEqual(@Param("pts") int pts);

    // 20. Clients dont les ptsAccumules entre deux valeurs
    @Query("SELECT c FROM Client c WHERE c.carteFidelite.pointsAcumules BETWEEN :min AND :max")
    List<Client> findByPtsAccumulesBetween(@Param("min") int min, @Param("max") int max);

    // 21. Clients ayant commandé un article par son nom
    @Query("SELECT DISTINCT c FROM Client c JOIN c.commandes cmd JOIN cmd.detailsCommande d JOIN d.article a WHERE a.nomArticle = :nomArticle")
    List<Client> findByCommandeArticleNom(@Param("nomArticle") String nomArticle);

    // 22. Clients dont nom contient une chaine et ont commandé des articles par type
    @Query("SELECT DISTINCT c FROM Client c " +
            "JOIN c.commandes cmd JOIN cmd.detailsCommande d JOIN d.article a " +
            "WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nomStr, '%')) AND a.typeArticle = :typeArticle")
    List<Client> findByNomContainingAndArticleType(@Param("nomStr") String nomStr, @Param("typeArticle") String typeArticle);
    // 3b. Trouver TOUS les clients par nom et prénom (NOUVELLE MÉTHODE)
    @Query("SELECT c FROM Client c WHERE c.nom = :nom AND c.prenom = :prenom")
    List<Client> findAllByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    @Query("SELECT c FROM Client c WHERE FUNCTION('MONTH', c.dateNaissance) = :month AND FUNCTION('DAY', c.dateNaissance) = :day")
    List<Client> findClientsByBirthday(int month, int day);

}
