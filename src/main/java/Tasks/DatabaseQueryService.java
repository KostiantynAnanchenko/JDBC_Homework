package Tasks;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public static void main(String[] args) {
        DatabaseQueryService dqs = new DatabaseQueryService();

        System.out.println(dqs.findMaxProjectsClient().toString());
        dqs.printProjectPrices();
        dqs.findLongestProject();
        dqs.findMaxSalaryWorker();
        dqs.findYoungestEldestWorkers();
    }

    public class MaxProjectCountClient {
        private String name;
        private int projectCount;

        public MaxProjectCountClient(String name, int projectCount) {
            this.name = name;
            this.projectCount = projectCount;
        }

        @Override
        public String toString() {
            return "MaxProjectCountClient{" +
                    "name='" + name + '\'' +
                    ", projectCount=" + projectCount +
                    '}';
        }
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> resultList = new ArrayList<>();

        try {
            Connection connection = Database.getInstance().getConnection();
            String query = new String(Files.readAllBytes(Paths.get("sql/find_max_projects_client.sql")));
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int projectCount = resultSet.getInt("project_count");
                resultList.add(new MaxProjectCountClient(name, projectCount));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public void findLongestProject() {
        try {
            Connection connection = Database.getInstance().getConnection();
            String query = new String(Files.readAllBytes(Paths.get("sql/find_longest_project.sql")));
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int projectId = resultSet.getInt(1);
                int monthCount = resultSet.getInt(2);
                System.out.println("Project ID: " + projectId + ", Month Count: " + monthCount);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void findMaxSalaryWorker() {
        try {
            Connection connection = Database.getInstance().getConnection();
            String query = new String(Files.readAllBytes(Paths.get("sql/find_max_salary_worker.sql")));
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("NAME") +
                        ", Salary: " + resultSet.getInt("SALARY"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void findYoungestEldestWorkers() {
        try {
            Connection connection = Database.getInstance().getConnection();
            String query = new String(Files.readAllBytes(Paths.get("sql/find_youngest_eldest_workers.sql")));
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Type: " + resultSet.getString("TYPE") +
                        ", Name: " + resultSet.getString("NAME") +
                        ", Birthday: " + resultSet.getString("BIRTHDAY"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void printProjectPrices() {
        try {
            Connection connection = Database.getInstance().getConnection();
            String query = new String(Files.readAllBytes(Paths.get("sql/print_project_prices.sql")));
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int projectId = resultSet.getInt(1);
                int projectPrice = resultSet.getInt(2);
                System.out.println("Project ID: " + projectId + ", Project Price: " + projectPrice);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}