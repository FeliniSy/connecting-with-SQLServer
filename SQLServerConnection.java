import java.sql.*;

public class SQLServerConnection {

    public static void main(String[] args) {

        String serverName = "FELINISY"; // e.g., localhost, yourserver.database.windows.net
        String databaseName = "Hospital";
        String username = "sa";
        String password = "!234";
        String portNumber = "1433"; // Default SQL Server port

        // SQL Server JDBC driver class
        String jdbcUrl = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true;"; // for Azure, add encrypt=true

        String sqlQuery = "select + from doctors";


        try {
            // Load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
//                System.out.println("Connected to SQL Server successfully!");
                // Perform database operations here (e.g., execute queries)

                // Example query:

                Statement statement = connection.createStatement();

                long startTime = System.currentTimeMillis();

                ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name FROM doctors");

                long endTime = System.currentTimeMillis();

                long executionTime = endTime - startTime;

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("first_name") + "         " + resultSet.getString("last_name"));
                }
                System.out.println("\n" + executionTime + " miliseconds");
                resultSet.close();
                statement.close();


                connection.close(); // Close the connection when done
            } else {
                System.out.println("Failed to connect to SQL Server.");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}