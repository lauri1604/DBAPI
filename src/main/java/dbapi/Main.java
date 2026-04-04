package dbapi;
import dbapi.database.DatabaseService;
import dbapi.repository.CharacterRepository;
import dbapi.repository.CharacterRepositoryImpl;
import dbapi.service.ExportService;
import dbapi.storage.CharactersStorage;
import dbapi.storage.PlanetStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        final Logger logger = LoggerFactory.getLogger(CharacterRepositoryImpl.class);
        DatabaseService db = new DatabaseService();
        CharacterRepository characterRepository = new CharacterRepositoryImpl(db);
        System.out.println("-----------    ¡BIENVENIDO A DBAPI (versión reducida)!    -----------");
        System.out.println("IMPORTANTE: Asegúrate de haber creado el schema dbapi para poder crear las tablas");
        try {
            CharactersStorage characters = new CharactersStorage();
            characters.cargarDatos();
            characters.guardarDatos();

            // Exportar personajes a CSV
            ExportService exportService = new ExportService(characterRepository);
            boolean exportSuccess = exportService.exportCharactersToCSV();
            if (exportSuccess) {
                System.out.println("✓ Personajes exportados a CSV exitosamente");
            } else {
                System.err.println("✗ Error al exportar personajes a CSV");
            }
        } catch (Exception e) {
            System.err.println("Error en Main: " + e.getMessage());
            e.printStackTrace();
        }

        //Consultas con streams
        System.out.println("Consultas con streams:");
        // 1. Filtrar Saiyans
        List<String> saiyan = characterRepository.findAll().stream().filter(c -> c.getRace() != null && c.getRace().equalsIgnoreCase("Saiyan")).map(c -> c.getName()).toList();
        System.out.println("Personajes de raza Saiyan: " + saiyan);
        // 2. Agrupar por raza
        List<String> razas = characterRepository.findAll().stream().filter(c -> c.getRace() != null).map(c -> c.getRace()).distinct().toList();
        System.out.println("Razas distintas: " + razas);
        // 3. Nº de personajes por afiliación
        Map<String, Long> p = characterRepository.findAll().stream().filter(c -> c.getAffiliation() != null).collect(Collectors.groupingBy(c -> c.getAffiliation(), Collectors.counting()));
        System.out.println("Número de personajes por afiliación: " + p);
    }
}