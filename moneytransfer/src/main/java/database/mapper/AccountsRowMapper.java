package database.mapper;

import model.Accounts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AccountsRowMapper {

    public Set<Accounts> dbMapper(ResultSet resultSet) throws SQLException {
        Set<Accounts> accounts= new HashSet<>();

        while (resultSet.next()){

            accounts.add(new Accounts(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),Float.valueOf(resultSet.getString(5)) ));
        }

        return  accounts;
    }
}
