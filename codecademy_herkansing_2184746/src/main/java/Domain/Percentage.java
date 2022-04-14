package Domain;

public class Percentage {
    private final int percentage;

    public Percentage(int number) {
        if (isValidPercentage(number)) {
            this.percentage = number;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getPercentage() {
        return percentage;
    }

    private static boolean isValidPercentage(int percentage) {
        return percentage >= 0 && percentage <= 100;
    }


}
