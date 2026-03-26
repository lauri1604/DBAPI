package dbapi.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbapi.database.DatabaseService;
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

        String sql = "SELECT * FROM personajes WHERE LOWER(nombre) LIKE LOWER(?)";
        return db.select(sql, "%" + nombre + "%").stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Character save(Character character) {
        logger.debug("Guardando personaje: " + (character != null ? character.getName() : "null"));
        String sql = "INSERT INTO characters (name, ki, maxKi, description, image, gender, race, affiliation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object generatedId = db.insertAndGetId(sql, character != null ? character.getid() : null,
                character != null ? character.getName() : null,
                character != null ? character.getKi() : null,
                character != null ? character.getMaxKi() : null,
                character != null ? character.getGender() : null,
                character != null ? character.getRace() : null,

        int id = -1;
        if (generatedId instanceof Number) {
            id = ((Number) generatedId).intValue();
            return findById(id);
        }

        return character;
    }

    @Override
    public Character update(int id, Character item) {
        return null;
    }

    @Override
    public Character delete(int id) {
        return null;
    }

    @Override
    public List<Character> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Character> findByGender(String gender) {
        return List.of();
    }

    @Override
    public List<Character> findByRace(String race) {
        return List.of();
    }

    @Override
    public List<Character> findByAffiliation(String affiliation) {
        return List.of();
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
