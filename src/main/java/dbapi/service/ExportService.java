package dbapi.service;
import dbapi.models.characters.Character;
import dbapi.repository.CharacterRepository;
import dbapi.repository.CharacterRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExportService {

    private final CharacterRepository characterRepository;
    private final Logger logger = LoggerFactory.getLogger(ExportService.class.getName());
    private static final String DATA_DIR = "data";
    private static final String CSV_FILENAME = "personajes.csv";

    public ExportService() throws URISyntaxException {
        this.characterRepository = new CharacterRepositoryImpl();
    }

    public ExportService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    /**
     * Exporta todos los personajes a un archivo CSV en la carpeta data.
     * 
     * @return true si la exportación fue exitosa, false en caso contrario
     */
    public boolean exportCharactersToCSV() {
        Path dataDir = Path.of(DATA_DIR);
        Path filePath = dataDir.resolve(CSV_FILENAME);
        return exportCharactersToCSV(filePath.toString());
    }

    /**
     * Exporta todos los personajes a un archivo CSV.
     * 
     * @param filePath ruta y nombre del archivo CSV
     * @return true si la exportación fue exitosa, false en caso contrario
     */
    public boolean exportCharactersToCSV(String filePath) {
        try {
            // Verificar que el directorio existe
            Path path = Path.of(filePath);
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) {
                logger.error("El directorio no existe: " + parent);
                return false;
            }

            List<Character> characters = characterRepository.findAll();
            
            if (characters.isEmpty()) {
                logger.warn("No hay personajes para exportar");
                return false;
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                // Escribir encabezados
                writeCSVHeader(writer);

                // Escribir datos
                for (Character character : characters) {
                    writeCharacterRow(writer, character);
                }

                logger.info("Personajes exportados exitosamente a: " + filePath);
                return true;
            }

        } catch (IOException e) {
            logger.error("Error al exportar personajes a CSV: " + e.getMessage());
            return false;
        }
    }

    /**
     * Escribe los encabezados del CSV.
     * 
     * @param writer FileWriter para escribir en el archivo
     * @throws IOException si ocurre un error al escribir
     */
    private void writeCSVHeader(FileWriter writer) throws IOException {
        String header = "ID,Name,Ki,MaxKi,Race,Gender,Description,Image,Affiliation\n";
        writer.write(header);
    }

    /**
     * Escribe una fila de personaje en el CSV.
     * 
     * @param writer FileWriter para escribir en el archivo
     * @param character personaje a escribir
     * @throws IOException si ocurre un error al escribir
     */
    private void writeCharacterRow(FileWriter writer, Character character) throws IOException {
        String row = String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s\n",
                character.getId(),
                escapeCSVValue(character.getName()),
                escapeCSVValue(character.getKi()),
                escapeCSVValue(character.getMaxKi()),
                escapeCSVValue(character.getRace()),
                escapeCSVValue(character.getGender()),
                escapeCSVValue(character.getDescription()),
                escapeCSVValue(character.getImage()),
                escapeCSVValue(character.getAffiliation()));
        writer.write(row);
    }

    /**
     * Escapa valores especiales en CSV (comillas y saltos de línea).
     * 
     * @param value valor a escapar
     * @return valor escapado
     */
    private String escapeCSVValue(String value) {
        if (value == null) {
            return "";
        }
        
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        
        return value;
    }
}
