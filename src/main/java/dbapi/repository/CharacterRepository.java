package dbapi.repository;
import dbapi.models.characters.Character;
import java.util.List;

public interface CharacterRepository extends CRUDRepository<Character> {
    List<Character> findByNombre(String nombre);

    //Búsquedas específicas
    List<Character> findByName(String name);
    List<Character> findByGender(String gender);
    List<Character> findByRace(String race);
    List<Character> findByAffiliation(String affiliation);
}
