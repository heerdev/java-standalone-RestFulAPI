package database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public interface DBEntityMapper<T> {

    public Set<T> dbMapper(ResultSet resultSet) throws SQLException;
}
