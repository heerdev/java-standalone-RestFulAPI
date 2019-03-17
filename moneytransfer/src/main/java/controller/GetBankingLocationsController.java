package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.mapper.BankLocationRowMapper;
import factory.DBConnectionFactory;
import model.BankLocation;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class GetBankingLocationsController implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        if(!t.getRequestMethod().equalsIgnoreCase("get")){
            t.sendResponseHeaders(404, "invalid request".length());
            OutputStream os = t.getResponseBody();
            os.write("invalid request".toString().getBytes());
            os.close();
        }

        String selectBankLocations="select * from bank_location";
        ResultSet resultSet= DBConnectionFactory.getResultSet(selectBankLocations);
        BankLocationRowMapper bankLocationRowMapper= new BankLocationRowMapper();
        try {
            Set<BankLocation> bankLocations=bankLocationRowMapper.dbMapper(resultSet);
            ObjectMapper mapper = new ObjectMapper();

            //Object to JSON in String
            String jsonInString = mapper.writeValueAsString(bankLocations);
            t.sendResponseHeaders(200, jsonInString.length());
            OutputStream os = t.getResponseBody();
            os.write(jsonInString.getBytes());
            os.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
