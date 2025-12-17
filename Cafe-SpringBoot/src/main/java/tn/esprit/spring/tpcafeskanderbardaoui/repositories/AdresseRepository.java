package tn.esprit.spring.tpcafeskanderbardaoui.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafeskanderbardaoui.entities.Adresse;

import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    // 1. Trouver toutes les adresses d'une ville spécifique
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville")
    List<Adresse> findByVille(@Param("ville") String ville);

    // 2. Trouver les adresses par code postal exact
    @Query("SELECT a FROM Adresse a WHERE a.codePostal = :codePostal")
    List<Adresse> findByCodePostal(@Param("codePostal") int codePostal);

    // 3. Compter le nombre d'adresses dans une ville
    @Query("SELECT COUNT(a) FROM Adresse a WHERE a.ville = :ville")
    long countByVille(@Param("ville") String ville);

    // 4. Supprimer toutes les adresses d'une ville
    @Modifying
    @Transactional
    @Query("DELETE FROM Adresse a WHERE a.ville = :ville")
    void deleteByVille(@Param("ville") String ville);

    // 5. Trouver les adresses d'une ville avec un code postal spécifique
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville AND a.codePostal = :codePostal")
    List<Adresse> findByVilleAndCodePostal(@Param("ville") String ville, @Param("codePostal") int codePostal);

    // 6. Rue contient un mot + insensible à la casse
    @Query("SELECT a FROM Adresse a WHERE LOWER(a.rue) LIKE LOWER(CONCAT('%', :word, '%'))")
    List<Adresse> findByRueContainingIgnoreCase(@Param("word") String word);

    // 7. Adresses d'une liste de villes
    @Query("SELECT a FROM Adresse a WHERE a.ville IN :villes")
    List<Adresse> findByVilleIn(@Param("villes") List<String> villes);

    // 8. Code postal dans une plage
    @Query("SELECT a FROM Adresse a WHERE a.codePostal BETWEEN :min AND :max")
    List<Adresse> findByCodePostalBetween(@Param("min") int min, @Param("max") int max);

    // 9. Code postal >
    @Query("SELECT a FROM Adresse a WHERE a.codePostal > :cp")
    List<Adresse> findByCodePostalGreaterThan(@Param("cp") int cp);

    // 10. Code postal >=
    @Query("SELECT a FROM Adresse a WHERE a.codePostal >= :cp")
    List<Adresse> findByCodePostalGreaterThanOrEqual(@Param("cp") int cp);

    // 11. Code postal <
    @Query("SELECT a FROM Adresse a WHERE a.codePostal < :cp")
    List<Adresse> findByCodePostalLessThan(@Param("cp") int cp);

    // 12. Code postal <=
    @Query("SELECT a FROM Adresse a WHERE a.codePostal <= :cp")
    List<Adresse> findByCodePostalLessThanOrEqual(@Param("cp") int cp);

    // 13. Rue commence par X + dans une ville + tri par code postal
    @Query("SELECT a FROM Adresse a WHERE a.ville = :ville AND a.rue LIKE CONCAT(:prefix, '%') ORDER BY a.codePostal")
    List<Adresse> findByRueStartingWithAndVilleOrderByCodePostal(
            @Param("prefix") String prefix,
            @Param("ville") String ville
    );

    // 14. Rue commence par chaîne
    @Query("SELECT a FROM Adresse a WHERE a.rue LIKE CONCAT(:prefix, '%')")
    List<Adresse> findByRueStartingWith(@Param("prefix") String prefix);

    // 15. Ville se termine par
    @Query("SELECT a FROM Adresse a WHERE a.ville LIKE CONCAT('%', :suffix)")
    List<Adresse> findByVilleEndingWith(@Param("suffix") String suffix);

    // 16. Rue est null
    @Query("SELECT a FROM Adresse a WHERE a.rue IS NULL")
    List<Adresse> findByRueIsNull();

    // 17. Ville n'est pas null
    @Query("SELECT a FROM Adresse a WHERE a.ville IS NOT NULL")
    List<Adresse> findByVilleIsNotNull();

}
