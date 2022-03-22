package Domain;

public abstract class ContentItem {

    private final String contentItemTitle;
    private final ValidatedDate publicationDate;
    private final Status status;

    public ContentItem(String contentItemTitle, ValidatedDate publicationDate, Status status) {
        this.contentItemTitle = contentItemTitle;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public ValidatedDate getPublicationDate() {
        return publicationDate;
    }

    public Status getStatus() {
        return status;
    }

}
