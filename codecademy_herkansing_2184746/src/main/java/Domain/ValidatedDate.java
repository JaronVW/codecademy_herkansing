package Domain;

import java.sql.Date;
import java.time.LocalDate;

public class ValidatedDate {
    private Date date;


    public ValidatedDate(int day, int month, int year) {
        if(validateDate(day,month,year)) {
            LocalDate localDate = LocalDate.of(year, month, day);
            this.date = Date.valueOf(localDate);
        }

    }

    public ValidatedDate(Date date) {
        if(date == null)
            throw new NullPointerException();
        this.date = date;
    }

    public static boolean validateDate(int day, int month, int year) {

        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getDate().toString();
    }
}
