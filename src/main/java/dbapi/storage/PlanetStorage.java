package dbapi.storage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbapi.models.characters.Response;
import dbapi.models.planets.ResponsePlanets;
import java.net.URL;
import java.util.List;

public class PlanetStorage {
    public void cargarDatos(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("\n" + "Todos los plaanetas de Dragon ball:");
            URL url = new URL("https://dragonball-api.com/api/planets?limit=20");
            ResponsePlanets r2 = objectMapper.readValue(url, ResponsePlanets.class);
            r2.getItems().forEach(System.out::println);
            System.out.println("Nº de total de planetas: " + r2.getItems().stream().count());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    }
