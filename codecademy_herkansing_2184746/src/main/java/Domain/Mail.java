package Domain;
public class Mail {

    public static void main(String[] args) {

        String email = "quinnie@hotmail.nl";
        String[] mail = email.split("@");
        mail = mail[1].split(".");
        String test = mail[0];
//        email.split("@")[1].split(".").length()
        System.out.println(test);

    }
    public static boolean validateMailAddress(String mailAddress) {
        return false;
    }
}
