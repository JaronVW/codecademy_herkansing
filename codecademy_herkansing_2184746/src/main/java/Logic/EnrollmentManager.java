package Logic;


import Database.EnrollmentDAO;
import Domain.Enrollment;

import java.util.ArrayList;

//manager classes are used to prevent code duplication and make code migration easier. they can also easily implement needed logic
public class EnrollmentManager {

    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentManager() {
        this.enrollmentDAO = new EnrollmentDAO();
    }

    public ArrayList<Enrollment> allEnrollments() {
        return enrollmentDAO.selectAllEnrollments();
    }
    //gets all enrollments
    public boolean newEnrollment(Enrollment enrollment) {
        return enrollmentDAO.insertEnrollment(enrollment);
    }
    //creates a new enrollment

    public boolean editEnrollment(Enrollment oldEnrollment, Enrollment newEnrollment) {
        return enrollmentDAO.updateEnrollment(oldEnrollment, newEnrollment);
    }
    //updates enrollment
    public boolean deleteEnrollment(Enrollment enrollment) {
        return enrollmentDAO.deleteEnrollment(enrollment.getEmailaddress());
    }
    //deletes enrollment
}
