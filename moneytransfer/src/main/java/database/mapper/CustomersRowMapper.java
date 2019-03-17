package database.mapper;
import model.Customers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CustomersRowMapper {


    public Set<Customers> dbMapper(ResultSet resultSet) throws SQLException {
        Set<Customers> customers= new HashSet<>();

        while (resultSet.next()){

            customers.add(new Customers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
        }

        return  customers;
    }
}
