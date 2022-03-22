package Domain;

public class Course {
    private final String courseName,subject,introText;
    private final int level;

    public Course(String courseName, String subject, String introText, int level) {
        this.courseName = courseName;
        this.subject = subject;
        this.introText = introText;
        this.level = level;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSubject() {
        return subject;
    }

    public String getIntroText() {
        return introText;
    }

    public int getLevel() {
        return level;
    }
}
