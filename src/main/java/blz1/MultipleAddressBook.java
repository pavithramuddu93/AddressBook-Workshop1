package blz1;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;

interface MultipleAddressBook {

    public void addAddressBook(String bookName, String firstName, String lastName, String address, String city, int zip,
                               String state, long phoneNumber, String email);

    public void getContact();

    boolean equals(String firstName);

    public void editContact();

    public void deleteEntry();

    public boolean makeChoice() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;

    public void viewPersonByCity();

    public void viewPersonByState();

    public void getContactByCityOrState();

    public void sortAlphabetically();

    public void sortCityStateOrZip();

    public void writeAddressBookInFiles(AddressBookImplement.IOService ioService);

    public void readAddressBookInFiles(AddressBookImplement.IOService ioService);

    public void writeAddressBookInCSV() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;

    public void readAddressBookInCSV() throws IOException;
}