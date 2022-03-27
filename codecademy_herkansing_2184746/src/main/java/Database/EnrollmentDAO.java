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
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT * FROM Enrollment");

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

    public boolean insertEnrollment(Enrollment enrollment) {
        try{
            String sql = "INSERT INTO Enrollment VALUES(?,?,?)";
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
        // inserts an enrollment to the database

    }

    public boolean deleteEnrollment(Mail mail) {
        try{
            String sql = "DELETE FROM Enrollment WHERE Emailaddress = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,mail.getMail());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        // deletes an enrollment from the database
    }

    public boolean updateEnrollment(Enrollment enrollment, Enrollment CurrentEnrollment {
        try{
            String sql = "UPDATE Enrollment SET Emailaddress = ?, CourseName = ?,RegisterDate =? WHERE Emailaddress = ? AND CourseName = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, enrollment.getEmailaddress().getMail());
            pstmt.setString(2,enrollment.getCourseName());
            pstmt.setDate(3,enrollment.getRegisterDate().getDate());
            pstmt.setString(4, CurrentEnrollment.getEmailaddress().getMail());
            pstmt.setString(4, CurrentEnrollment.getCourseName());
            pstmt.executeUpdate();
            return true;

        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        // updates an enrollment from the database
    }

}
