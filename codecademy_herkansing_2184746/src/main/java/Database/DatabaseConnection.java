package Database;

import java.sql.*;

public class DatabaseConnection {

    private final String connectionUrl;
    private Connection con;


    public DatabaseConnection() {
        connectionUrl = "jdbc:sqlserver://localhost;databaseName=codecademy;integratedSecurity=true;";
    }

    public Boolean openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ResultSet executeSelectStatement(String query) {
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

    public boolean executeInsertStatement(String query) {
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

    public boolean executeUpdateStatement(String query) {
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

    public boolean executeDeleteStatement(String query) {
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