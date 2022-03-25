package Domain;

public class Enrollment {
    private Mail emailaddress;
    private String courseName;
    private ValidatedDate registerDate;


    public Enrollment(Mail emailaddress, String courseName, ValidatedDate registerDate) {
        this.emailaddress = emailaddress;
        this.courseName = courseName;
        this.registerDate = registerDate;
    }

    public Mail getEmailaddress() {
        return emailaddress;
    }

    public String getCourseName() {
        return courseName;
    }

    public ValidatedDate getRegisterDate() {
        return registerDate;
    }
}
