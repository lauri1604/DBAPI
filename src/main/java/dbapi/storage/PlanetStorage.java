package dbapi.storage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbapi.models.planets.Meta;
import java.net.URL;
import java.util.List;

public class PlanetStorage {
    public void cargarDatos(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = new URL("https://dragonball-api.com/api/planets");
            List<Meta> List = objectMapper.readValue(url, new TypeReference<List<Meta>>() {});
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    }
