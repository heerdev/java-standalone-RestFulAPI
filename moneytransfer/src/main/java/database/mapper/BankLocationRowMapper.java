package database.mapper;

import model.BankLocation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BankLocationRowMapper {

    public Set<BankLocation> dbMapper(ResultSet resultSet) throws SQLException {
        Set<BankLocation> bankLocations= new HashSet<>();

        while (resultSet.next()){

            bankLocations.add(new BankLocation(resultSet.getInt(1),resultSet.getString(2)));
        }

        return  bankLocations;
    }
}
