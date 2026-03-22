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
            URL url = new URL("https://dragonball-api.com/api/characters");
            List<Response> List = objectMapper.readValue(url, new TypeReference<List<Response>>() {});
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
