package Domain;

import java.sql.Date;
import java.time.LocalDate;

public class ValidatedDate {
    private Date date;


    public ValidatedDate(int day, int month, int year) {
        if(validateDate(day,month,year))
            this.date = new Date(day,month,year);

    }

    public ValidatedDate(Date date) {
            this.date = date;
    }



    public Date getDate() {
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
