package blz1;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.*;

public class AddressBookImplement implements MultipleAddressBook {

    public enum IOService{CONSOLE_IO,FILE_IO,DB_IO,REST_IO}

    public Map<String, AddressBook> book;
    public Map<String, ArrayList<AddressBook>> multibook;
    public Map<String, ArrayList<AddressBook>> city;
    public Map<String, ArrayList<AddressBook>> state;
    public Map<Integer, ArrayList<AddressBook>> zip;
    public ArrayList<AddressBook> entries;
    public int count = 0;
    Scanner obj = new Scanner(System.in);

    // Constructor
    public AddressBookImplement() {
        book = new HashMap<>();
        multibook = new HashMap<>();
        city = new HashMap<>();
        state = new HashMap<>();
        zip = new HashMap<>();
        entries = new ArrayList<>();
    }


    @Override
    public void addAddressBook(String bookName, String firstName, String lastName, String address, String city, int zip,
                               String state, long phoneNumber, String email) {
        AddressBook adder = new AddressBook(bookName, firstName, lastName, address, city, zip, state, phoneNumber, email);
        entries.add(adder);
        book.put(firstName, adder);
        multibook.put(bookName, entries);
        this.city.put(city, entries);
        this.state.put(state, entries);
        this.zip.put(zip, entries);
        count++;
    }

    //This method takes console arguments
    @Override
    public void getContact() {
        System.out.println("Enter Address Book Name");
        String bookName = obj.next();
        System.out.println("Enter you first name");
        String firstName = obj.next();

        if (equals(firstName)) {
            System.out.println("Enter you last name");
            String lastName = obj.next();
            obj.nextLine();
            System.out.println("Enter you Address name");
            String address = obj.nextLine();
            System.out.println("Enter you zip ");
            int zip = obj.nextInt();
            System.out.println("Enter you city name");
            String city = obj.next();
            System.out.println("Enter you state name");
            String state = obj.next();
            obj.nextLine();
            System.out.println("Enter you phone number");
            long phoneNumber = obj.nextLong();
            obj.nextLine();
            System.out.println("Enter you email name");
            String email = obj.nextLine();
            addAddressBook(bookName, firstName, lastName, address, city, zip, state, phoneNumber, email);
        }
        else
            System.out.println("the Name already exist in contact please use different name");
    }

    @Override
    public boolean equals(String firstName) {
        AddressBook details = book.get(firstName);
        if (details == null) return true;
        return false;
    }

    //    This method helps to edit the details
    @Override
    public void editContact() {
        System.out.println("enter your book name");
        String bookname = obj.next();
        ArrayList<AddressBook> option = multibook.get(bookname);
        System.out.println("enter your name");
        String name = obj.next();
        for (AddressBook details : option) {
            if (details.firstName.equals(name)) {
                boolean conditon = true;
                while (conditon) {
                    System.out.println("enter number  1:first_name 2:last_name 3:address 4:City 5:zip 6:state 7:phone_number" +
                            " 8:email  0:quit");
                    int check = obj.nextInt();
                    switch (check) {
                        case 1:
                            System.out.println("Enter you first name");
                            String firstname = obj.next();
                            details.firstName = firstname;
                            System.out.println(book);
                            break;
                        case 2:
                            System.out.println("Enter you last name");
                            String lastname = obj.next();
                            details.firstName = lastname;
                            System.out.println(book);
                            break;
                        case 3:
                            System.out.println("Enter you address ");
                            String addressname = obj.next();
                            details.address = addressname;
                            System.out.println(book);
                            break;
                        case 4:
                            System.out.println("Enter you City name");
                            String cityname = obj.next();
                            details.city = cityname;
                            System.out.println(book);
                            break;
                        case 5:
                            System.out.println("Enter you Zip name");
                            int zipname = obj.nextInt();
                            details.zip = zipname;
                            System.out.println(book);
                            break;
                        case 6:
                            System.out.println("Enter you State name");
                            String statename = obj.next();
                            details.state = statename;
                            System.out.println(book);
                            break;
                        case 7:
                            System.out.println("Enter you Phone number");
                            long phonenumber = obj.nextLong();
                            obj.nextLine();
                            details.phoneNumber = phonenumber;
                            System.out.println(book);
                            break;
                        case 8:
                            System.out.println("Enter you email");
                            String emailname = obj.next();
                            details.email = emailname;
                            System.out.println(book);
                            break;
                        case 0:
                            conditon = false;
                            break;
                        default:
                            System.out.println("invalid input");
                    }
                }
            }
        }
    }

    // This method helps to delete the contact details
    @Override
    public void deleteEntry() {
        System.out.println("enter your name to delete from contact");
        String name = obj.next();
        AddressBook a = book.get(name);
        if(equals(a)) {
            System.out.println("Entry Not Exist");
        }
        else
            book.remove(a);
    }

    // This method helps user to choose action
    public boolean makeChoice() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        System.out.println("enter 1:add_contact \n2:view_by_city \n3-view_by_state \n4:edit_contact \n5:delete_contact" +
                " \n6:person_by_city_or_state \n7:get_count_of_person \n8:sort_alphabetically \n9:sort_viaCityStateZip \n10: Write address in system file \n11: Read address in system file \n12: Write Data Into CSV File \n13: Read Data From CSV File \n0 to quit");
        int check = obj.nextInt();
        boolean conditon = true;
        switch (check) {
            case 1:
                getContact();
                break;
            case 2:
                viewPersonByCity();
                break;
            case 3:
                viewPersonByState();
                break;
            case 4:
                editContact();
                break;
            case 5:
                deleteEntry();
                break;
            case 6:
                getContactByCityOrState();
                break;
            case 7:
                getCountOfPersons();
                break;
            case 8:
                sortAlphabetically();
                break;
            case 9:
                sortCityStateOrZip();
                break;
            case 10:
                writeAddressBookInFiles(IOService.FILE_IO);
                break;
            case 11:
                readAddressBookInFiles(IOService.FILE_IO);
                break;
            case 12:
                writeAddressBookInCSV();
                break;
            case 13:
                readAddressBookInCSV();
                break;
            case 0:
                conditon = false;
                break;
            default:
                System.out.println("invalid input");
        }
        return conditon;
    }

    private void getCountOfPersons() {
        System.out.println("total count is " + count);
    }

    public void viewPersonByCity() {
        System.out.println("Enter city");
        String location = obj.next();
        obj.nextLine();
        List exit = city.get(location);
        if (exit != null)
            System.out.println(city.get(location));
        else
            System.out.println("no records found");
    }

    public void viewPersonByState() {
        System.out.println("Enter state");
        String location = obj.next();
        obj.nextLine();
        List exit = state.get(location);
        if (exit != null)
            System.out.println(state.get(location));
        else
            System.out.println("no records found");
    }

    public void getContactByCityOrState() {
        System.out.println("Enter city or state");
        String location = obj.next();
        obj.nextLine();
        List check1 = state.get(location);
        List check2 = city.get(location);
        if (check1 != null)
            System.out.println(state.get(location));
        else if (check2 != null)
            System.out.println(city.get(location));
        else
            System.out.println("no records found");
    }

    public void sortAlphabetically() {
        book.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
    }

    public void sortCityStateOrZip() {
        System.out.println("sort by 1:city 2:state 3:zip");
        int check = obj.nextInt();
        switch (check) {
            case 1:
                city.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(System.out::println);
                break;
            case 2:
                state.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(System.out::println);
                break;
            case 3:
                zip.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(System.out::println);
                break;
        }
    }

    public void writeAddressBookInFiles(IOService ioService){
        if (ioService.equals(IOService.FILE_IO)) {
            new AdressBookFileIO().writeData(entries);
            System.out.println("Data stored successfully in /IdeaProjects/AddressBook/AddressBook.txt");
        }
    }

    public void readAddressBookInFiles(IOService ioService){
        if (ioService.equals(IOService.FILE_IO)) {
            new AdressBookFileIO().readData();
            System.out.println("Data Read successfully From /IdeaProjects/AddressBook/AddressBook.txt");
        }
    }

    public void writeAddressBookInCSV() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        OpenCSVWriter openCSVWriter = new OpenCSVWriter();
        openCSVWriter.writeData();
    }

    public void readAddressBookInCSV() throws IOException {
        OpenCSVWriter openCSVWriter = new OpenCSVWriter();
        openCSVWriter.readData();
    }
}