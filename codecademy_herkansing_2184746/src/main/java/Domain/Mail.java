package Domain;
public class Mail {

    private final String mail;

    public Mail(String mail) {

        // TODO add validation for mail
        this.mail = mail;
    }

    @Override
    public String toString() {
        return getMail();
    }

    public String getMail() {
        return mail;
    }
}
