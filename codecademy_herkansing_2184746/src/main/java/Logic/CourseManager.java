package Logic;

import Database.CourseDAO;
import Domain.Course;

import java.util.ArrayList;

//manager classes are used to prevent code duplication and make code migration easier. they can also easily implement needed logic
public class CourseManager {

    private final CourseDAO courseDAO;

    public CourseManager() {
        this.courseDAO = new CourseDAO();
    }

    public ArrayList<Course> allCourses(){
        return courseDAO.selectAllCourses();
    }
    //gets all courses

    public String numberOfCompletedCourses(String courseName){
        return "number completed: " + courseDAO.numberofCompletedCourses(courseName)+ " Student(s)";
    }
    //gets number of students that completed a course

}
