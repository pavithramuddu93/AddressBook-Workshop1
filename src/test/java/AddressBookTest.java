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


}
