package Domain;

public class Student {
    private final Mail emailaddress;
    private final String firstname,lastname;
    private final ValidatedDate dateOfBirth;
    private final Gender gender;
    private final String address;
    private final Zipcode zipcode;
    private final String city, country;

    public Student(Mail emailaddress, String firstname, String lastname, ValidatedDate dateOfBirth, Gender gender, String address, Zipcode zipcode, String city, String country) {
        this.emailaddress = emailaddress;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public Mail getEmailaddress() {
        return emailaddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public ValidatedDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public Zipcode getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }


    @Override
    public String toString() {
        return "Student{" +
                "emailaddress=" + emailaddress +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", DateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
