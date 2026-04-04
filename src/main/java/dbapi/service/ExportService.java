package dbapi.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dbapi.models.characters.Character;
import dbapi.repository.CharacterRepository;
import dbapi.repository.CharacterRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportService {
    private final CharacterRepository characterRepository;
    private final Logger logger = LoggerFactory.getLogger(ExportService.class.getName());
    private static final String DATA_DIR = "data";
    private static final String CSV_FILENAME = "personajes.csv";
    private final ObjectMapper mapper;

    public boolean exportCharactersToCSV() {
        Path dataDir = Path.of(DATA_DIR);
        Path filePath = dataDir.resolve(CSV_FILENAME);
        return exportCharactersToCSV(filePath.toString());
    }

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

    private void writeCSVHeader(FileWriter writer) throws IOException {
        String header = "ID,Name,Ki,MaxKi,Race,Gender,Description,Image,Affiliation\n";
        writer.write(header);
    }

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

    private String escapeCSVValue(String value) {
        if (value == null) {
            return "";
        }

        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    public ExportService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    //exportar la BD a un archivo JSON
    public void exportToJson(String filename) {
        logger.info("Exportando a JSON el archivo {}", filename);
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("characters", characterRepository.findAll());
            //Aqui creamos el archivo
            File file = new File(filename);
            mapper.writeValue(file, data);
            logger.info("Exportado correctamente :)");
        } catch (Exception e) {
            logger.error("Error en la exportación :(");
        }
    }
}