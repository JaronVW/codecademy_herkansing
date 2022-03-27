package Domain;

public class Webcast extends ContentItem {

    private final int duration;
    private final String webcastURL;
    private final Mail speakerEmail;
    private final String webcastDescription;

    public Webcast(String coursename,
                   String contentItemTitle,
                   ValidatedDate publicationDate,
                   Status status,
                   Mail emailaddress,
                   int percentage,
                   int duration,
                   String webcastURL,
                   Mail speakerEmail,
                   String webcastDescription) {
        super(coursename, contentItemTitle, publicationDate, status, emailaddress, percentage);
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
