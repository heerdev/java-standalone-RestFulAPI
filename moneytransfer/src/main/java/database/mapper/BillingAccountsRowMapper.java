package database.mapper;


import model.BillingAccounts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BillingAccountsRowMapper {

    public Set<BillingAccounts> dbMapper(ResultSet resultSet) throws SQLException {
        Set<BillingAccounts> billingAccounts= new HashSet<>();

        while (resultSet.next()){

            billingAccounts.add(new BillingAccounts(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),Float.valueOf(resultSet.getString(4)),resultSet.getString(5)));
        }

        return  billingAccounts;
    }
}
