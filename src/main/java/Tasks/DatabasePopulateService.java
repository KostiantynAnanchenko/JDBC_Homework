package Tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {

    public static void main(String[] args) {
        String populateScriptPath = "sql/populate_db.sql";

        try {
            Connection connection = Database.getInstance().getConnection();
            String sqlScript = new String(Files.readAllBytes(Paths.get(populateScriptPath)));
            String[] sqlCommands = sqlScript.split(";");

            for (String command : sqlCommands) {
                if (!command.trim().isEmpty()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(command);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
            }

            System.out.println("Database populated successfully.");

            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}