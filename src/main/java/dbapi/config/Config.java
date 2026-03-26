package dbapi.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class.getName());
    private static String databaseUrl = "jdbc:mysql://localhost:3306/dbapi";
    private static boolean databaseInitTables = true;
    private static boolean databaseInitData = true;
    private static String storageData = "data";
    private static String databaseUser = "root";
    private static String databasePassword = "root";

    static {
        try {
            logger.debug("Cargando configuración de la BD");
            Properties properties = new Properties();
            InputStream input = ClassLoader.getSystemResourceAsStream("config.properties");
            if (input != null) {
                properties.load(input);
                databaseUrl = properties.getProperty("database.url", databaseUrl);
                databaseInitTables = Boolean.parseBoolean(properties.getProperty("database.init.tables", String.valueOf(databaseInitTables)));
                databaseInitData = Boolean.parseBoolean(properties.getProperty("database.init.data", String.valueOf(databaseInitData)));
                storageData = properties.getProperty("storage.data", storageData);
                databaseUser = properties.getProperty("database.user", databaseUser);
                databasePassword = properties.getProperty("database.password", databasePassword);
                logger.debug("Configuración cargada al 100%");
            } else {
                logger.error("Archivo de configuración 'config.properties' no existe o no se pudo leer");
            }
        } catch (Exception e) {
            logger.error("Error cargando configuración: " + e.getMessage());
        }
    }

    public static String getDatabaseUrl() {
        return databaseUrl;
    }
    public static boolean getDatabaseInitTables() {
        return databaseInitTables;
    }
    public static boolean getDatabaseInitData() {
        return databaseInitData;
    }

    public static boolean isDatabaseInitTables() {
        return databaseInitTables;
    }

    public static boolean isDatabaseInitData() {
        return databaseInitData;
    }

    public static String getStorageData() {
        return storageData;
    }

    public static String getDatabaseUser() {
        return databaseUser;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }
}
