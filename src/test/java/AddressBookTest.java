import blz1.AddressBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import blz1.AddressBookImplement;

public class AddressBookTest {

    @Test
    void fromAddressBookDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookImplement addressBookImplement = new AddressBookImplement();
        List<AddressBook> addressBookList = addressBookImplement.readAddressBookData();
        System.out.println(addressBookList);
        Assertions.assertEquals(3, addressBookList.size());
    }

    @Test
    void givenNewAddressForContact_whenUpdate_shouldSyncWithDB() {
        AddressBookImplement addressBookImplement = new AddressBookImplement();
        List<AddressBook> employeePayrollData = addressBookImplement.readAddressBookData();
        addressBookImplement.updateAddressBook("ram", "Dehli");
        boolean result = addressBookImplement.checkAddressBookSyncWithDB("ram");
        Assertions.assertEquals(true, result);
    }

    @Test
    void givenStateForAddressBookDB_WhenRetrieved_ShouldMatchExpectedCount() {
        AddressBookImplement addressBookImplement = new AddressBookImplement();
        String state = "mp";
        List<AddressBook> addressBookList = addressBookImplement.readFilteredAddressBookData(state);
        System.out.println(addressBookList);
        Assertions.assertEquals(2, addressBookList.size());
    }

    @Test
    void givenNewContactToDB_whenAdded_shouldSyncWithDB () {
        AddressBookImplement addressBookImplement = new AddressBookImplement();
        addressBookImplement.readAddressBookData();
        addressBookImplement.addContacts("book3","Rohit","Maywade","mumbai","mumbai","mh",456536,345634566,"gg@gmail.com");
        boolean result = addressBookImplement.checkAddressBookSyncWithDB("Rohit");
        Assertions.assertTrue(result);
    }

    @Test
    void givenNewContactToAddressBookDB_whenAdded_shouldMatchWithEntries() {
        AddressBook[] addressBooks = {
                new AddressBook("book0", "Rohit", "Maywade", "mumbai", "mumbai", 456536, "mh", 345634566, "gg@gmail.com"),
                new AddressBook("book4", "gsdf", "Maywade", "mumbai", "mumbai", 456536, "mh", 345634566, "gg@gmail.com"),
                new AddressBook("book5", "dgdf", "Maywade", "mumbai", "mumbai", 456536, "mh", 345634566, "gg@gmail.com"),
                new AddressBook("book6", "dfgf", "Maywade", "mumbai", "mumbai", 456536, "mh", 345634566, "gg@gmail.com"),
                new AddressBook("book7", "dfg", "Maywade", "mumbai", "mumbai", 456536, "mh", 345634566, "gg@gmail.com"),
                new AddressBook("book8", "dfg", "Maywade", "mumbai", "mumbai", 456536, "mh", 345634566, "gg@gmail.com")
        };
        AddressBookImplement addressBookImplement = new AddressBookImplement();
        addressBookImplement.readAddressBookData();
        Instant threadStart = Instant.now();
        addressBookImplement.addContactDBWithThreads(Arrays.asList(addressBooks));
        Instant threadEnd = Instant.now();
        System.out.println("Duration with thread  " + Duration.between(threadStart, threadEnd));
        Assertions.assertEquals(11, addressBookImplement.countEntries());
    }
}