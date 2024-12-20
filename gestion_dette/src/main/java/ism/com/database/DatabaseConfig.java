package ism.com.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement du fichier db.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
