package Database;

import java.sql.*;

public class DatabaseConnection {

    private final String connectionUrl;
    private Connection con;


    public DatabaseConnection() {
        connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;";
    }

    public Connection openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    private ResultSet executeSelectStatement(String query) {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            statement.close();
            return resultSet;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
        // method returns ResultSet if query execution was successful
    }

    private boolean executeInsertStatement(String query) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private boolean executeUpdateStatement(String query) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private boolean executeDeleteStatement(String query) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

}