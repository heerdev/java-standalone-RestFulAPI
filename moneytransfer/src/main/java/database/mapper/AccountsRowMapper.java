package database.mapper;

import edu.umd.cs.findbugs.annotations.DefaultAnnotationForMethods;
import javafx.beans.DefaultProperty;
import model.Accounts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class AccountsRowMapper implements DBEntityMapper<Accounts> {

    @Override
    public Set<Accounts> dbMapper(ResultSet resultSet) throws SQLException {
        Set<Accounts> accounts= new HashSet<>();

        while (resultSet.next()){

            accounts.add(new Accounts(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),Float.valueOf(resultSet.getString(5)) ));
        }

        return  accounts;
    }



}
