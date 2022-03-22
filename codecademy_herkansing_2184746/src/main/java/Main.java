import Database.DatabaseConnection;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        if (databaseConnection.openConnection()) {
            ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT * from Student");
            try {
                while (resultSet.next())
                    System.out.println(resultSet.getString("Emailaddress"));
            } catch (Exception e) {
                System.out.println("error" + e);
            }
        }

    }
}
