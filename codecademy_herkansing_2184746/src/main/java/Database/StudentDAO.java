package Database;

import Domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentDAO {
    // student data acces object
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
        // method receives ResultSet with database data and iterates over each entry to fill arraylist. The arraylist gets returned to method call
    }

    public ArrayList<ContentItemProgress> selectStudentModuleProgress(Mail mail) {
        ArrayList<ContentItemProgress> contentItemProgressArrayList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ContentItemProgress WHERE Emailaddress=" + mail.getMail();
            ResultSet resultSet = databaseConnection.executeSelectStatement(sql);
            while (resultSet.next()) {
                contentItemProgressArrayList.add(new ContentItemProgress(
                        resultSet.getInt("ContentItemID"),
                        new Mail(resultSet.getString("Emailaddress")),
                        resultSet.getInt("Percentage")
                ));
            }
            return contentItemProgressArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertStudent(Student student) {
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
        // inserts a student from the database
    }

    public boolean deleteStudent(Mail mail) {

        try {
            String sql = "DELETE FROM Student WHERE Emailaddress = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, mail.getMail());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // deletes a student from the database

    }

    public boolean updateStudent(Student student, Mail oldMail) {
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
        // deletes a student from the database
    }

    public HashMap<String, ContentItemProgress> selectModulePercentagePerCourse(String courseName, String emailaddress) {
        HashMap<String, ContentItemProgress> result = new HashMap<>();

        ResultSet resultSet = databaseConnection.executeSelectStatement("SELECT ContentItem.CourseName, ContentItem.ContentItemID,ContentItemProgress.Emailaddress,Percentage\n" +
                "FROM ContentItem\n" +
                "         JOIN ContentItemProgress ON ContentItem.ContentItemID = ContentItemProgress.ContentItemID\n" +
                "         JOIN Course ON Course.CourseName = ContentItem.CourseName\n" +
                "         JOIN Module ON Module.ContentItemID = ContentItemProgress.ContentItemID\n" +
                "         WHERE Course.courseName = '"+courseName + "' AND ContentItemProgress.Emailaddress = '"+emailaddress +  "'");
        try {
            while (resultSet.next()) {
                result.put(
                        resultSet.getString("CourseName"),
                        new ContentItemProgress(
                                resultSet.getInt("ContentItemID"),
                                new Mail(resultSet.getString("Emailaddress")),
                                resultSet.getInt("Percentage")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            return result;
        }
        return result;
    }

}


