package Database;

import Domain.Mail;
import Domain.Status;
import Domain.ValidatedDate;
import Domain.Webcast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class WebcastDAO {
    private final DatabaseConnection databaseConnection;
    private Connection connection;

    public WebcastDAO() {
        this.databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.openConnection();
    }

    public ArrayList<Webcast> selectAllWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT DISTINCT * FROM ContentItem,Webcast WHERE ContentItem.ContentItemID = Webcast.ContentItemID");
        try {
            while (resultSet.next()) {
                webcasts.add(new Webcast(
                                resultSet.getString("CourseName"),
                                resultSet.getString("ContentItemTitle"),
                                new ValidatedDate(resultSet.getDate("publicationDate")),
                                Status.valueOf(resultSet.getString("Status")),
                                resultSet.getInt("Duration"),
                                resultSet.getString("WebcastURL"),
                                new Mail(resultSet.getString("speakerEmail")),
                                resultSet.getString("WebcastDescription")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return webcasts;
        // method receives ResultSet and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }

    public HashMap<Integer, String> selectTopFourWebcasts() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        String query = "SELECT TOP 4 ContentItem.ContentItemID, ContentItemTitle, SUM(Percentage) AS PercentageSUM FROM ContentItem\n" +
                "JOIN Webcast W on ContentItem.ContentItemID = W.ContentItemID\n" +
                "JOIN ContentItemProgress CIP on ContentItem.ContentItemID = CIP.ContentItemID\n" +
                "GROUP BY ContentItem.ContentItemID, ContentItemTitle\n" +
                "ORDER BY PercentageSUM desc";
        ResultSet resultSet = databaseConnection.executeSelectStatement(query);
        try {
            while (resultSet.next())
                hashMap.put(resultSet.getInt("ContentItemID"), resultSet.getString("ContentItemTitle"));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return hashMap;
    }

}
