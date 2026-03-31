package dbapi;
import dbapi.service.ExportService;
import dbapi.storage.CharactersStorage;
import dbapi.storage.PlanetStorage;

public class Main {
    public static void main(String[] args) {
        try {
            CharactersStorage characters = new CharactersStorage();
            characters.cargarDatos();
            characters.guardarDatos();

            // Exportar personajes a CSV
            ExportService exportService = new ExportService();
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
