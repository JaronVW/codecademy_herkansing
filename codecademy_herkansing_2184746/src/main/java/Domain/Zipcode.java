package Domain;

public class Zipcode {

    String zipcode;

    public Zipcode(String postalCode) {
        postalCode = formatPostalCode(postalCode);
        this.zipcode = postalCode;
    }

    public String getZipcode() {
        return zipcode;
    }

    private static String formatPostalCode(String postalCode) {
        if (postalCode == null) {
            throw new NullPointerException();
        }
        postalCode = postalCode.replaceAll("\\s", "");

        String[] numbers = postalCode.split("[^0-9]+");
        String[] letters = postalCode.split("[0-9]+");

        if (numbers.length == 0 || letters.length == 0) {
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(numbers[0]) > 999 && Integer.parseInt(numbers[0]) <= 9999) {
            if (letters[1].length() == 2) {
                letters[1] = letters[1].toUpperCase();
                return numbers[0] + letters[1];
            }
        }
        throw new IllegalArgumentException();
    }
}
