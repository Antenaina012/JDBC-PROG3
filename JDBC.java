import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDBConnectorNoComments {

    private static final String DB_URL = "postgresql-42.7.8.jar";
    private static final String USER = "Antenaina";
    private static final String PASSWORD = "Lamiloyk@5";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            System.out.println("Tentative de connexion...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Connexion à PostgreSQL établie avec succès.");
                testQuery(connection);
            } else {
                System.out.println("Échec de l'établissement de la connexion.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL :");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur inattendue :");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connexion fermée.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion :");
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testQuery(Connection connection) {
        String SQL_QUERY = "SELECT version();";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {

            System.out.println("\n--- Résultat du test ---");
            if (resultSet.next()) {
                String dbVersion = resultSet.getString(1);
                System.out.println("Version de PostgreSQL : " + dbVersion);
            }
            System.out.println("------------------------\n");

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution du test de requête :");
            e.printStackTrace();
        }
    }
}