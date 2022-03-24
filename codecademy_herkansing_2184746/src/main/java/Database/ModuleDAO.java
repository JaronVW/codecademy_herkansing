package Database;

import Domain.Course;
import Domain.Module;
import Domain.Status;
import Domain.ValidatedDate;

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
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT ContentItemTitle, PublicationDate, Module.Status, Version, ModuleDescription, ContactpersonEmail\n" +
                "FROM Module JOIN ContentItem on ContentItem.ContentItemID = Module.ContentItemID");
        // selects necessary data to instantiate a Module object
        try {
            while (resultSet.next()) {
                String contentItemTitle = resultSet.getString("ContentItemTitle");
                ValidatedDate publicationDate = new ValidatedDate(resultSet.getDate("PublicationDate"));
                Status status = Status.valueOf(resultSet.getString("Status"));
                int version = resultSet.getInt("Version");
                String moduleDescription = resultSet.getString("ModuleDescription");
                String contactPersonEmail = resultSet.getString("contactPersonEmail");

                modules.add(new Module(contentItemTitle,publicationDate,status,version,moduleDescription,contactPersonEmail));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modules;
        // return an arraylist with Modules
    }


}
