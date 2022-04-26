package blz1;
public class AddressBook {
    public String bookName;
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public int zip;
    public String state;
    public long phoneNumber;
    public String email;

    public AddressBook(String bookName, String firstName, String lastName, String address, String city, int zip,
                       String state, long phoneNumber, String email) {
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "bookName='" + bookName + '\'' +
                "firstName='" + firstName + '\'' +
                "lastName='" + lastName + '\'' +
                "address='" + address + '\'' +
                "city='" + city + '\'' +
                "zip=" + zip +
                "state='" + state + '\'' +
                "phoneNumber=" + phoneNumber +
                "email='" + email + '\'' +
                '}';
    }
}