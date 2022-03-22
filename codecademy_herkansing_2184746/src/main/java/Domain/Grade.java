package Domain;

public class Grade {
    private final int number;

    public Grade(int number) {
        if (isValidPercentage(number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public int getNumber() {
        return number;
    }

    private static boolean isValidPercentage(int percentage) {
        return percentage >= 0 && percentage <= 100;
    }


}
