package blz;
public class AddressBookMain {
    public static void main(String[] args){
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