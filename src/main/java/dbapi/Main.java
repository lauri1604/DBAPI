package dbapi;
import dbapi.database.DatabaseService;
import dbapi.repository.CharacterRepository;
import dbapi.repository.CharacterRepositoryImpl;
import dbapi.service.ExportService;
import dbapi.storage.CharactersStorage;
import dbapi.storage.PlanetStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        final Logger logger = LoggerFactory.getLogger(CharacterRepositoryImpl.class);
        DatabaseService db = new DatabaseService();
        CharacterRepository characterRepository = new CharacterRepositoryImpl(db);
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
    }
}
