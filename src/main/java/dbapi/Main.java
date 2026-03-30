package dbapi;
import dbapi.storage.CharactersStorage;
import dbapi.storage.PlanetStorage;

public class Main {
    public static void main(String[] args) {
        try {
            CharactersStorage characters = new CharactersStorage();
            characters.cargarDatos();
        } catch (Exception e) {
            System.err.println("Error en Main: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
