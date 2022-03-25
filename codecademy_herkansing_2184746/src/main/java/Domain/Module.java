package Domain;

public class Module extends ContentItem {

    private final int version;
    private final String moduleDescription, contactPersonEmail;

    public Module(String coursename,
                  String contentItemTitle,
                  ValidatedDate publicationDate,
                  Status status,
                  Mail emailaddress,
                  int percentage,
                  int version,
                  String moduleDescription,
                  String contactPersonEmail) {

        super(coursename, contentItemTitle, publicationDate, status, emailaddress, percentage);
        this.version = version;
        this.moduleDescription = moduleDescription;
        this.contactPersonEmail = contactPersonEmail;
    }

    public int getVersion() {
        return version;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }


}
