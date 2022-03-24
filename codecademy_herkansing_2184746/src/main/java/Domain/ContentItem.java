package Domain;

import java.util.ArrayList;

public abstract class ContentItem {

    private final String contentItemTitle;
    private final ValidatedDate publicationDate;
    private final Status status;
    private ArrayList<Webcast> webcasts;
    private ArrayList<Module> modules;

    public ContentItem(String contentItemTitle, ValidatedDate publicationDate, Status status) {
        this.contentItemTitle = contentItemTitle;
        this.publicationDate = publicationDate;
        this.status = status;
        this.webcasts = new ArrayList<>();
        this.modules = new ArrayList<>();
    }

    public ValidatedDate getPublicationDate() {
        return publicationDate;
    }

    public Status getStatus() {
        return status;
    }

}
