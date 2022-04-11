package Domain;

public abstract class ContentItem {

    protected final String coursename;
    protected final String contentItemTitle;
    protected final ValidatedDate publicationDate;
    protected final Status status;


    public ContentItem(String coursename, String contentItemTitle, ValidatedDate publicationDate, Status status) {
        this.coursename = coursename;
        this.contentItemTitle = contentItemTitle;
        this.publicationDate = publicationDate;
        this.status = status;
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

}
