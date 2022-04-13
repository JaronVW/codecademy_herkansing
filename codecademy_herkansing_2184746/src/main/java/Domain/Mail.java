package Domain;
public class Mail {

    private final String mail;

    public Mail(String mail) {
        if(mail.matches("^\\S+@\\S+\\.\\S+$"))
            this.mail = mail;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return getMail();
    }

    public String getMail() {
        return mail;
    }
}
