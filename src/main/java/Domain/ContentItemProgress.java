package Domain;

public class ContentItemProgress {
    private final int contentItemID;
    private final Mail emailaddress;
    private final int percentage;

    public ContentItemProgress(int contentItemID, Mail emailaddress, int percentage) {
        this.contentItemID = contentItemID;
        this.emailaddress = emailaddress;
        this.percentage = percentage;
    }

    public int getContentItemID() {
        return contentItemID;
    }

    public Mail getEmailaddress() {
        return emailaddress;
    }

    public int getPercentage() {
        return percentage;
    }
}
