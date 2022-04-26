package blz1;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.List;

interface MultipleAddressBook {

    void addAddressBook(String bookName, String firstName, String lastName, String address, String city, int zip,
                        String state, long phoneNumber, String email);
    void getContact();

    boolean equals(String firstName);

    void editContact();

    void deleteEntry();

    boolean makeChoice() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;

    void viewPersonByCity();

    void viewPersonByState();

    void getContactByCityOrState();

    void sortAlphabetically();

    void sortCityStateOrZip();

    void writeAddressBookInFiles(AddressBookImplement.IOService ioService);

    void readAddressBookInFiles(AddressBookImplement.IOService ioService);

    void writeAddressBookInCSV() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;

    void readAddressBookInCSV() throws IOException;

    List<AddressBook> readAddressBookData();

    boolean checkAddressBookSyncWithDB(String name);

    void updateAddressBook(String name, String address);

    AddressBook getAddressBookData(String name);

    List<AddressBook> readFilteredAddressBookData(String stateName);

    void addContacts(String bookName, String firstName, String lastName, String add, String city, String state, int zip, long phone, String email);

    long countEntries();

    void addContactDBWithThreads(List<AddressBook> addressBooks);
}