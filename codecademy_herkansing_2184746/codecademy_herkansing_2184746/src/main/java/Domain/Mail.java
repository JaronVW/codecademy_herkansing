package Domain;
public class Mail {

    private final String mail;

    public Mail(String mail) {

        // TODO add validation for mail
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
