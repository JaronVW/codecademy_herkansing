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
    //selects all students
    public boolean newStudent(Student student) {
        return studentDAO.insertStudent(student);
    }
    //adds new student
    public boolean editStudent(Student oldStudent, Student newStudent) {
        return studentDAO.updateStudent(oldStudent, newStudent.getEmailaddress());
    }
    //updates student
    public boolean deleteStudent(Student student) {
        return studentDAO.deleteStudent(student.getEmailaddress());
    }
    //deletes student
    public HashMap<String, ContentItemProgress> studentModulePercentage(String courseName, String emailaddress) {
        return studentDAO.selectModulePercentagePerCourse(courseName, emailaddress);
    }
    //shows percentage completed of student for specific modules

}
