package Domain;

import java.sql.Date;
import java.time.LocalDate;

public class ValidatedDate {
    private Date date;


    public ValidatedDate(int day, int month, int year) {
        if (validateDateInt(day, month, year)) {
            LocalDate localDate = LocalDate.of(year, month, day);
            this.date = Date.valueOf(localDate);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public ValidatedDate(Date date) {
        if (date == null)
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

    public static boolean validateDateInt(int day, int month, int year) {
        // months with 31 days
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12 && 1 <= day && day <= 31) {
            return true;
            // months with 30 days
        } else if (month == 4 || month == 6 || month == 9 || month == 11 && 1 <= day && day <= 30) {
            return true;
        } else if (month == 2 && 1 <= day && day <= 29 && year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            // accepts 29 february if leap year
            return true;
        } else if (month == 2 && 1 <= day && day <= 28 && year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)){
            return true;
        }
        return false;
    }


    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getDate().toString();
    }
}
