package database.mapper;

import model.AccountType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AccountTypeRowMapper {

    public Set<AccountType> dbMapper(ResultSet resultSet) throws SQLException {
        Set<AccountType> accountTypes= new HashSet<>();

        while (resultSet.next()){
            accountTypes.add(new AccountType(resultSet.getInt(1),resultSet.getString(5)));
        }
        return  accountTypes;
    }
}
