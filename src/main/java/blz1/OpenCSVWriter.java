package blz1;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {
    public static final String OBJECT_LIST = "object_list_AddressBook.csv";

    public void writeData() throws IOException,
            CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
        try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST))
        ){

            StatefulBeanToCsv<AddressBook> beanToCsv = new StatefulBeanToCsvBuilder<AddressBook>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

//            AddressBookImplement addressBookImplement = new AddressBookImplement();
//            addressBookImplement.getContact();
//            beanToCsv.write(addressBookImplement.entries);
//            System.out.println("Data Stored SuccessFully");

            List<AddressBook> addressBookList = new ArrayList<>();
            addressBookList.add(new AddressBook("Book1","Deep","Maywade","Betul","Betul",2432,"mp",34534534,"dee@gmail.com"));
            addressBookList.add(new AddressBook("Book1","Deep","Maywade","Betul","Betul",2432,"mp",34534534,"dee@gmail.com"));
            System.out.println(addressBookList.get(0).toString());
            beanToCsv.write(addressBookList);
        }
    }

    public void readData() throws IOException {
        try(
                Reader reader = Files.newBufferedReader(Paths.get(OBJECT_LIST));
                CSVReader csvReader = new CSVReader(reader)
        ){
            List<String[]> records = csvReader.readAll();

            for (String[] record : records){
                System.out.println("Address: "+record[0]);
                System.out.println("BookName: "+record[1]);
                System.out.println("City: "+record[2]);
                System.out.println("Email: "+record[3]);
                System.out.println("FirstName: "+record[4]);
                System.out.println("LastName: "+record[5]);
                System.out.println("PhoneNo: "+record[6]);
                System.out.println("State: "+record[7]);
                System.out.println("Zip: "+record[8]);
                System.out.println("======================");
            }
        }
    }



}