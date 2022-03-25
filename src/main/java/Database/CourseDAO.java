package Database;

import Domain.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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
                courses.add(new Course(courseName,subject,introText,level));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courses;
        // method receives ResultSet and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }




}
