package Domain;

public abstract class ContentItem {

    protected final String courseName;
    protected final String contentItemTitle;
    protected final ValidatedDate publicationDate;
    protected final Status status;


    public ContentItem(String courseName, String contentItemTitle, ValidatedDate publicationDate, Status status) {
        this.courseName = courseName;
        this.contentItemTitle = contentItemTitle;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
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
}
