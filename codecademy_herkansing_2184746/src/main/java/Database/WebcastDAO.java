package Database;

import Domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WebcastDAO {
    private final DatabaseConnection databaseConnection;
    private Connection connection;

    public WebcastDAO() {
        this.databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.openConnection();
    }

    public ArrayList<Webcast> selectAllWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT * FROM Webcast");
        try {
            while (resultSet.next()) {
                String courseName = resultSet.getString("CourseName");
                String contentItemTitle = resultSet.getString("ContentItemTitle");
                ValidatedDate publicationDate = new ValidatedDate(resultSet.getDate("publicationDate"));
                Status status = Status.valueOf(resultSet.getString("Status"));
                Mail mail = new Mail(resultSet.getString("Emailaddress"));
                int percentage = resultSet.getInt("Percentage");
                int duration = resultSet.getInt("Duration");
                String webcastURL = resultSet.getString("WebcastURL");
                Mail speakerEmail = new Mail(resultSet.getString("speakerEmail"));
                String webcastDescription = resultSet.getString("WebcastDescription");

                webcasts.add(new Webcast(courseName,
                        contentItemTitle,
                        publicationDate,
                        status,
                        mail,
                        percentage,
                        duration,
                        webcastURL,
                        speakerEmail,
                        webcastDescription));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return webcasts;
        // method receives ResultSet and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }

}
