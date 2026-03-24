package dbapi.storage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbapi.models.characters.Response;
import java.net.URL;
import java.util.List;

public class CharactersStorage {
    public void cargarDatos(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("Todos los personajes de Dragon ball:");
            URL url = new URL("https://dragonball-api.com/api/characters?limit=58");
            Response r = objectMapper.readValue(url, Response.class);
            r.getItems().forEach(System.out::println);
            System.out.println("Nº de total de personajes: " + r.getItems().stream().count());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}