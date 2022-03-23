package Database;

import Domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
    private final DatabaseConnection databaseConnection;
    private Connection connection;

    public StudentDAO() {
        this.databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.openConnection();
    }

    public ArrayList<Student> selectAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT * FROM Student");

        try {
            while (resultSet.next()) {
                Mail emailaddress = new Mail(resultSet.getString("Emailaddress"));
                String firstname = resultSet.getString("Firstname");
                String lastname = resultSet.getString("Lastname");
                ValidatedDate dateOfBirth = new ValidatedDate(resultSet.getDate("DateOfBirth"));
                Gender gender = Gender.valueOf(resultSet.getString("Gender"));
                String address = resultSet.getString("Address");
                Zipcode zipcode = new Zipcode(resultSet.getString("Zipcode"));
                String city = resultSet.getString("City");
                String country = resultSet.getString("country");

                students.add(new Student(emailaddress, firstname, lastname, dateOfBirth, gender, address, zipcode, city, country));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return students;
        // method receives ResultSet and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }

    public Boolean insertStudent(Student student) {
        try {
            String sql = "INSERT INTO Student VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getEmailaddress().getMail());
            pstmt.setString(2, student.getFirstname());
            pstmt.setString(3, student.getLastname());
            pstmt.setDate(4, student.getDateOfBirth().getDate());
            pstmt.setString(5, student.getGender().toString());
            pstmt.setString(6, student.getAddress());
            pstmt.setString(7, student.getZipcode().getZipcode());
            pstmt.setString(8, student.getCity());
            pstmt.setString(9, student.getCountry());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public Boolean deleteStudent(Student student) {

        try {
            String sql = "DELETE FROM Student WHERE Emailaddress = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getEmailaddress().getMail());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean updateStudent(Student student, Mail oldMail) {
        try {
            String sql = "Update Student SET " +
                    "Emailaddress = ?," +
                    "Firstname = ?," +
                    "Lastname = ?," +
                    "DateOfBirth = ?," +
                    "Gender = ?," +
                    "Address = ?," +
                    "Zipcode = ?," +
                    "City = ?," +
                    "Country =?  WHERE Emailaddress = ?";


            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getEmailaddress().getMail());
            pstmt.setString(2, student.getFirstname());
            pstmt.setString(3, student.getLastname());
            pstmt.setDate(4, student.getDateOfBirth().getDate());
            pstmt.setString(5, student.getGender().toString());
            pstmt.setString(6, student.getAddress());
            pstmt.setString(7, student.getZipcode().getZipcode());
            pstmt.setString(8, student.getCity());
            pstmt.setString(9, student.getCountry());
            pstmt.setString(10, oldMail.getMail());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
