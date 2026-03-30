package dbapi.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbapi.database.DatabaseService;
import dbapi.models.characters.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CharacterRepositoryImpl implements CharacterRepository {
    private final DatabaseService db;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(CharacterRepositoryImpl.class.getName());

    //Constructor por defecto
    public CharacterRepositoryImpl() throws URISyntaxException {
        this.db = new DatabaseService();
    }

    // Constructor para inyección de dependencia
    public CharacterRepositoryImpl(DatabaseService db) {
        this.db = db;
    }

    @Override
    public List<Character> findAll() {
        logger.info("Buscando todos los personajes");
        String sql = "SELECT * FROM characters";
        return db.select(sql).stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public Character findById(int id) {
        logger.info("Buscando personaje por id: " + id);
        String sql = "SELECT * FROM characters WHERE id = ?";
        List<Map<String, Object>> result = db.select(sql, id);
        if (result.isEmpty())
            return null;
        return toModel(result.get(0));
    }

    @Override
    public List<Character> findByNombre(String nombre) {
        logger.debug("Buscando personajes por nombre: " + nombre);

        String sql = "SELECT * FROM characters WHERE LOWER(name) LIKE LOWER(?)";
        return db.select(sql, "%" + nombre + "%").stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Character save(Character character) {
        logger.debug("Guardando personaje: " + (character != null ? character.getName() : "null"));
        String sql = "INSERT INTO characters (name, ki, maxKi, description, image, gender, race, affiliation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object generatedId = db.insertAndGetId(sql,
                character != null ? character.getName() : null,
                character != null ? character.getKi() : null,
                character != null ? character.getMaxKi() : null,
                character != null ? character.getDescription() : null,
                character != null ? character.getImage() : null,
                character != null ? character.getGender() : null,
                character != null ? character.getRace() : null,
                character != null ? character.getAffiliation() : null);

        int id = -1;
        if (generatedId instanceof Number) {
            id = ((Number) generatedId).intValue();
            return findById(id);
        }

        return character;
    }

    @Override
    public Character update(int id, Character item) {
        logger.debug("Actualizando personaje con id: " + id);
        String sql = "UPDATE characters SET name=?, ki=?, maxKi=?, description=?, image=?, gender=?, race=?, affiliation=? WHERE id=?";
        db.update(sql,
                item.getName(),
                item.getKi(),
                item.getMaxKi(),
                item.getDescription(),
                item.getImage(),
                item.getGender(),
                item.getRace(),
                item.getAffiliation(),
                id);
        return findById(id);
    }

    @Override
    public Character delete(int id) {
        logger.debug("Eliminando personaje con id: " + id);
        Character character = findById(id);
        String sql = "DELETE FROM characters WHERE id=?";
        db.update(sql, id);
        return character;
    }

    @Override
    public List<Character> findByName(String name) {
        logger.debug("Buscando personajes por nombre: " + name);
        String sql = "SELECT * FROM characters WHERE LOWER(name) LIKE LOWER(?)";
        return db.select(sql, "%" + name + "%").stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> findByGender(String gender) {
        logger.debug("Buscando personajes por género: " + gender);
        String sql = "SELECT * FROM characters WHERE LOWER(gender) = LOWER(?)";
        return db.select(sql, gender).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> findByRace(String race) {
        logger.debug("Buscando personajes por raza: " + race);
        String sql = "SELECT * FROM characters WHERE LOWER(race) = LOWER(?)";
        return db.select(sql, race).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> findByAffiliation(String affiliation) {
        logger.debug("Buscando personajes por afiliación: " + affiliation);
        String sql = "SELECT * FROM characters WHERE LOWER(affiliation) = LOWER(?)";
        return db.select(sql, affiliation).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    // Convierte una fila (Map) a CharactersItem usando ObjectMapper
    private Character toModel(Map<String, Object> entity) {
        try {
            // ObjectMapper convierte mapas a POJO aprovechando las anotaciones de Jackson
            return objectMapper.convertValue(entity, Character.class);
        } catch (IllegalArgumentException ex) {
            logger.warn("Error convirtiendo fila a CharactersItem: " + ex.getMessage());
            return null;
        }
    }
}
