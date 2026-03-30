package dbapi.storage;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbapi.models.characters.Response;
import dbapi.repository.CharacterRepository;
import dbapi.repository.CharacterRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;

public class CharactersStorage {
    private final Logger logger = LoggerFactory.getLogger(CharactersStorage.class.getName());
    private final CharacterRepository characterRepository;

    public CharactersStorage() throws Exception {
        this.characterRepository = new CharacterRepositoryImpl();
    }

    public void cargarDatos(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Iniciando carga de personajes de Dragon Ball desde API");
            URI uri = new URI("https://dragonball-api.com/api/characters?limit=58");
            Response r = objectMapper.readValue(uri.toURL(), Response.class);
            
            logger.info("Guardando " + r.getItems().size() + " personajes en la BD");
            r.getItems().forEach(character -> {
                characterRepository.save(character);
                logger.debug("Personaje guardado: " + character.getName());
            });
            
            logger.info("Total de personajes guardados: " + r.getItems().size());
            System.out.println("✓ " + r.getItems().size() + " personajes guardados en la BD");
        }catch(Exception e){
            logger.error("Error cargando datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}