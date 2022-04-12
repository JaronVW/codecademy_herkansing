package Database;

import Domain.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO {
    // course data acces object
    private DatabaseConnection databaseConnection;
    private Connection connection;

    public CourseDAO() {
        this.databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.openConnection();
    }

    public ArrayList<Course> selectAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT * FROM Course");

        try {
            while (resultSet.next()) {
                String courseName = resultSet.getString("CourseName");
                String subject = resultSet.getString("Subject");
                String introText = resultSet.getString("IntroText");
                int level = resultSet.getInt("Level");
                courses.add(new Course(courseName, subject, introText, level));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courses;
        // method receives ResultSet and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }

    public int numberofCompletedCourses(String courseName) {
        int result = 0;
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT Count(Course.CourseName) AS CompletedCourses\n" +
                "                FROM ContentItemProgress\n" +
                "                         JOIN ContentItem On ContentItem.ContentitemID = ContentItemProgress.ContentItemID\n" +
                "                         JOIN Course ON Course.CourseName = ContentItem.CourseName\n" +
                "                         WHERE Percentage = 100 AND Course.CourseName = '"+ courseName + "'");
        try {
            while (resultSet.next()) {
                result = resultSet.getInt("CompletedCourses");
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        }
    }


}
