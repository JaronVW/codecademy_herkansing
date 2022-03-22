package Domain;

import java.time.LocalDate;

public class ValidatedDate {
    private java.util.Date date;


    public ValidatedDate(int day, int month, int year) {
        if(validateDate(day,month,year))
            this.date = new java.util.Date(day,month,year);

    }

    public java.util.Date getDate() {
        return date;
    }

    public static boolean validateDate(int day, int month, int year) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
