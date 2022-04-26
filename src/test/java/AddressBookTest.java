import blz1.AddressBook;
import blz1.AddressBookImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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


}