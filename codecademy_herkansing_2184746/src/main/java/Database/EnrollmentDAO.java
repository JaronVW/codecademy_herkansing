package Database;

import Domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public Boolean insertEnrollment(Enrollment enrollment) {
        try{
            String sql = "INSERT INTO StudentCourseRegister VALUES(?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, enrollment.getEmailaddress().getMail());
            pstmt.setString(2,enrollment.getCourseName());
            pstmt.setDate(3,enrollment.getRegisterDate().getDate());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }


    }

    public Boolean deleteStudent(Student student) {
        try{
            String sql = "DELETE FROM StudentCourseRegister WHERE Emailaddress = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            return true;
        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean updateStudent(Enrollment enrollment, Mail CurrentEnrollmentMail) {
        try{
            String sql = "UPDATE StudentCourseRegister SET Emailaddress = ?, CourseName = ?,RegisterDate =? WHERE Emailaddress = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, enrollment.getEmailaddress().getMail());
            pstmt.setString(2,enrollment.getCourseName());
            pstmt.setDate(3,enrollment.getRegisterDate().getDate());
            pstmt.setString(4, CurrentEnrollmentMail.getMail());
            pstmt.executeUpdate();
            return true;

        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

}
