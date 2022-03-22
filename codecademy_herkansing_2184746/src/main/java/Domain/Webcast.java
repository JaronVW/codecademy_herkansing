package Domain;

public class Webcast extends ContentItem {

    private final int duration;
    private final String webcastURL;
    private final Mail speakerEmail;
    private final String webcastDescription;

    public Webcast(String contentItemTitle, ValidatedDate publicationDate, Status status, int duration, String webcastURL, Mail speakerEmail, String webcastDescription) {
        super(contentItemTitle, publicationDate, status);
        this.duration = duration;
        this.webcastURL = webcastURL;
        this.speakerEmail = speakerEmail;
        this.webcastDescription = webcastDescription;
    }

    public Mail getSpeakerEmail() {
        return speakerEmail;
    }

    public String getWebcastDescription() {
        return webcastDescription;
    }

    public int getDuration() {
        return duration;
    }

    public String getWebcastURL() {
        return webcastURL;
    }
}
