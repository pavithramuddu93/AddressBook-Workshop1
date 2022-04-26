package blz1;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;

public class AddressBookMain {
    public static void main(String[] args) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        AddressBookImplement bookBuilder=new AddressBookImplement();
        bookBuilder.makeChoice();
        boolean conditon=true;
        while (conditon) {
            boolean condition = bookBuilder.makeChoice();
            if (condition == false)
                break;
        }
    }
}