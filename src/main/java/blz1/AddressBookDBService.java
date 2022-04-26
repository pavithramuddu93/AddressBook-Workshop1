package blz1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private PreparedStatement addressBookDataStatement;

    private Connection getConnection() throws SQLException {
        String jdbcULR = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
        String userName = "root";
        String password = "Deep@123";
        Connection connection;
        System.out.println("Connecting To DB: " + jdbcULR);
        connection = DriverManager.getConnection(jdbcULR,userName,password);
        System.out.println("Connection is successful..! " + connection);
        return connection;
    }


    public ArrayList<AddressBook> readData() {
        String sql = "select * from address_book;";
        List<AddressBook> addressBookList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookList = this.getAddressBookData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (ArrayList<AddressBook>) addressBookList;
    }

    private List<AddressBook> getAddressBookData(ResultSet resultSet) {
        List<AddressBook>addressBookList = new ArrayList<>();
        try {
            while (resultSet.next()){
                String bookName = resultSet.getString("book_name");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                Long phoneNo = resultSet.getLong("phone_number");
                String email = resultSet.getString("email");
                addressBookList.add(new AddressBook(bookName,firstName,lastName,address,city,zip,state,phoneNo,email));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return addressBookList;
    }

    public int updateAdressBookData(String name, String address) {
        String sql = String.format("update address_book set address = '%s' where first_name = '%s';",address,name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<AddressBook> getAddressBookData(String name) {
        List<AddressBook>addressBookList = null;
        if (this.addressBookDataStatement==null)
            this.preparedStatementForAddressBookData();
        try {
            addressBookDataStatement.setString(1,name);
            ResultSet resultSet = addressBookDataStatement.executeQuery();
            addressBookList = this.getAddressBookData(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return addressBookList;
    }

    private void preparedStatementForAddressBookData() {
        try {
            Connection connection = this.getConnection();
            String sql = "select * from address_book where first_name= ?;";
            addressBookDataStatement = connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<AddressBook> readFilteredData(String stateName) {
        String sql = String.format("select * from address_book where state = '%s';",stateName);
        List<AddressBook> addressBookList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookList = this.getAddressBookData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (ArrayList<AddressBook>) addressBookList;
    }
}