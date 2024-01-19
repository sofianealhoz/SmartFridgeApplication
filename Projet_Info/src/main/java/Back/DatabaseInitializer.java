package Back;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS ingredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "expiration_date TEXT," +
                        "quantity DOUBLE," +
                        "category TEXT)";
                statement.execute(createTableSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

