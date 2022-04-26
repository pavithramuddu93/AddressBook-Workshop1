package blz1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private Connection getConnection() throws SQLException {
        String jdbcULR = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
        String userName = "root";
        String password = "Deep@123";
        Connection connection;
        System.out.println("Connecting To DB: " + jdbcULR);
        connection = DriverManager.getConnection(jdbcULR, userName, password);
        System.out.println("Connection is successful..! " + connection);
        return connection;
    }


    public ArrayList<AddressBook> readData() {
        String sql = "select * from address_book;";
        List<AddressBook> addressBookList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookList = this.getAddressBookData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (ArrayList<AddressBook>) addressBookList;
    }

    private List<AddressBook> getAddressBookData(ResultSet resultSet) {
        List<AddressBook> addressBookList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String bookName = resultSet.getString("book_name");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                Long phoneNo = resultSet.getLong("phone_number");
                String email = resultSet.getString("email");
                addressBookList.add(new AddressBook(bookName, firstName, lastName, address, city, zip, state, phoneNo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookList;
    }
}