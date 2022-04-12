package Logic;

import Database.StudentDAO;
import Domain.ContentItemProgress;
import Domain.Student;

import java.util.ArrayList;
import java.util.HashMap;

//manager classes are used to prevent code duplication and make code migration easier. they can also easily implement needed logic
public class StudentManager {

    private final StudentDAO studentDAO;

    public StudentManager() {
        this.studentDAO = new StudentDAO();
    }

    public ArrayList<Student> allStudents() {
        return studentDAO.selectAllStudents();
    }

    public boolean newStudent(Student student) {
        return studentDAO.insertStudent(student);
    }

    public boolean editStudent(Student oldStudent, Student newStudent) {
        return studentDAO.updateStudent(oldStudent, newStudent.getEmailaddress());
    }

    public boolean deleteStudent(Student student) {
        return studentDAO.deleteStudent(student.getEmailaddress());
    }

    public HashMap<String, ContentItemProgress> studentModulePercentage(String courseName, String emailaddress) {
        return studentDAO.selectModulePercentagePerCourse(courseName, emailaddress);
    }

}
