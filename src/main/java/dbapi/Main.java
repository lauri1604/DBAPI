package dbapi;

import dbapi.storage.CharactersStorage;
import dbapi.storage.PlanetStorage;

public class Main {
    public static void main(String[] args) {
        CharactersStorage characters = new CharactersStorage();
        characters.cargarDatos();
        PlanetStorage planets = new PlanetStorage();
        planets.cargarDatos();
    }
    }