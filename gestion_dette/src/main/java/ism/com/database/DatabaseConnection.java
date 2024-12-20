package ism.com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = DatabaseConfig.get("db.url");
                String username = DatabaseConfig.get("db.username");
                String password = DatabaseConfig.get("db.password");
                String driver = DatabaseConfig.get("db.driver");

                // Chargement du driver
                Class.forName(driver);

                // Établissement de la connexion
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connexion réussie à la base de données !");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur de connexion à la base de données");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
