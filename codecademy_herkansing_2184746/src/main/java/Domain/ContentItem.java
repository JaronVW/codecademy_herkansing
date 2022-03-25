package Domain;

import java.util.ArrayList;

public abstract class ContentItem {

    private final String coursename;
    private final String contentItemTitle;
    private final ValidatedDate publicationDate;
    private final Status status;
    private final Mail emailaddress;
    private final int Percentage;


    public ContentItem(String coursename, String contentItemTitle, ValidatedDate publicationDate, Status status, Mail emailaddress, int percentage) {
        this.coursename = coursename;
        this.contentItemTitle = contentItemTitle;
        this.publicationDate = publicationDate;
        this.status = status;
        this.emailaddress = emailaddress;
        Percentage = percentage;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getContentItemTitle() {
        return contentItemTitle;
    }

    public ValidatedDate getPublicationDate() {
        return publicationDate;
    }

    public Status getStatus() {
        return status;
    }


    public Mail getEmailaddress() {
        return emailaddress;
    }

    public int getPercentage() {
        return Percentage;
    }
}
