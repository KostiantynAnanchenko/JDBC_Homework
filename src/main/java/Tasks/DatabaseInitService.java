package Tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {

    public static void main(String[] args) {
        try {
            Connection connection = Database.getInstance().getConnection();
            String sqlContent = new String(Files.readAllBytes(Paths.get("sql/init_db.sql")));

            String[] sqlQueries = sqlContent.split(";");
            for (String query : sqlQueries) {
                if (!query.trim().isEmpty()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
            }

            System.out.println("Database initialized successfully");

            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}