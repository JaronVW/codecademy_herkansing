package Database;

import java.sql.*;

public class DatabaseConnection {

    private final String connectionUrl;
    private Connection connection;


    public DatabaseConnection() {
        connectionUrl = "jdbc:sqlserver://localhost;databaseName=codecademy;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        // string used to connect to database
    }

    public Connection openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl);
            //checks if the connection is valid and authenticated , then sets the connection to the variable
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        // method used to end the connection with the database
    }

    public ResultSet executeSelectStatement(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
        // method returns ResultSet if query execution was successful
    }
}