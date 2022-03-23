package Database;

import Domain.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollmentDAO {
    private final DatabaseConnection databaseConnection;
    private Connection connection;

    public EnrollmentDAO() {
        this.databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.openConnection();
    }

    public ArrayList<Enrollment> selectAllEnrollments() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT * FROM StudentCourseRegister");

        try {
            while (resultSet.next()) {
                Mail emailaddress = new Mail(resultSet.getString("Emailaddress"));
                String courseName = resultSet.getString("CourseName");
                ValidatedDate registerDate = new ValidatedDate(resultSet.getDate("RegisterDate"));
                enrollments.add(new Enrollment(emailaddress, courseName, registerDate));
            }
        } catch (SQLException e) {
                System.out.println(e);
        }
        return enrollments;
        // method receives ResultSet and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }

    public Boolean insertStudent(Student student) {
        return false;
    }

    public Boolean deleteStudent(Student student) {
        return false;
    }

    public Boolean updateStudent(Student student) {
        return false;
    }

}
