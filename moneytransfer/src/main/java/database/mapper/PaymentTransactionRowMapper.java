package database.mapper;


import model.PaymentTransaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PaymentTransactionRowMapper {

    public Set<PaymentTransaction> dbMapper(ResultSet resultSet) throws SQLException {
        Set<PaymentTransaction> paymentTransactions= new HashSet<>();

        while (resultSet.next()){

            paymentTransactions.add(new PaymentTransaction(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getDate(5),Float.valueOf(resultSet.getString(6)),resultSet.getString(7), resultSet.getString(8),resultSet.getString(9)));
        }
        return  paymentTransactions;
    }
}
