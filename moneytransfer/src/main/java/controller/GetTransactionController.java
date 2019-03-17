package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.mapper.BankLocationRowMapper;
import database.mapper.PaymentTransactionRowMapper;
import factory.DBConnectionFactory;
import model.BankLocation;
import model.PaymentTransaction;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class GetTransactionController  implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if(!httpExchange.getRequestMethod().equalsIgnoreCase("get")){
            httpExchange.sendResponseHeaders(404, "invalid request".length());
            OutputStream os = httpExchange.getResponseBody();
            os.write("invalid request".toString().getBytes());
            os.close();
        }

        URI uri = httpExchange.getRequestURI();
        System.err.println("URI: " + uri);

        String command = uri.toString().substring(uri.toString().indexOf('?') + 1);
        System.err.println("Command: " + command);


        String selectBankLocations="select * from payment_transaction";
        ResultSet resultSet= DBConnectionFactory.getResultSet(selectBankLocations);
        PaymentTransactionRowMapper paymentTransactionRowMapper= new PaymentTransactionRowMapper();
        try {
            Set<PaymentTransaction> paymentTransactions=paymentTransactionRowMapper.dbMapper(resultSet);
            ObjectMapper mapper = new ObjectMapper();

            //Object to JSON in String
            String jsonInString = mapper.writeValueAsString(paymentTransactions);
            httpExchange.sendResponseHeaders(200, jsonInString.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(jsonInString.getBytes());
            os.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }






    }
}
