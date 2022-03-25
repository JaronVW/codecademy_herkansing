package Database;

import Domain.*;
import Domain.Module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModuleDAO {

    private DatabaseConnection databaseConnection;
    private Connection connection;

    public ModuleDAO() {
        this.databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.openConnection();
    }

    public ArrayList<Module> selectAllModules() {
        ArrayList<Module> modules = new ArrayList<>();
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT CourseName," +
                " ContentItemTitle," +
                " PublicationDate," +
                " Module.Status," +
                " Emailaddress," +
                " Percentage," +
                " Version," +
                " ModuleDescription," +
                " ContactpersonEmail\n" +
                "FROM Module\n" +
                "JOIN ContentItem on ContentItem.ContentItemID = Module.ContentItemID\n" +
                "" +
                "JOIN ContentItemProgress  on ContentItem.ContentItemID = ContentItemProgress.ContentItemID");
        // selects necessary data to instantiate a Module object
        try {
            while (resultSet.next()) {
                String courseName = resultSet.getString("CourseName");
                String contentItemTitle = resultSet.getString("ContentItemTitle");
                ValidatedDate publicationDate = new ValidatedDate(resultSet.getDate("PublicationDate"));
                Status status = Status.valueOf(resultSet.getString("Status"));
                Mail emailaddress = new Mail(resultSet.getString("Emailaddress"));
                int percentage = resultSet.getInt("Percentage");
                int version = resultSet.getInt("Version");
                String moduleDescription = resultSet.getString("ModuleDescription");
                String contactPersonEmail = resultSet.getString("contactPersonEmail");

                modules.add(new Module(courseName, contentItemTitle, publicationDate, status,emailaddress,percentage, version, moduleDescription, contactPersonEmail));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modules;
        // return an arraylist with Modules
    }


}
