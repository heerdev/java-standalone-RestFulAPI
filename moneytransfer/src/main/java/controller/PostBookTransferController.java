package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.mapper.AccountsRowMapper;
import database.mapper.DBEntityMapper;
import factory.DBConnectionFactory;
import model.Accounts;
import model.BankLocation;
import model.MoneyTransferBook;
import model.PaymentTransaction;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PostBookTransferController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if(!httpExchange.getRequestMethod().equalsIgnoreCase("post")){
            httpExchange.sendResponseHeaders(404, "invalid request".length());
            OutputStream os = httpExchange.getResponseBody();
            os.write("invalid request".getBytes());
            os.close();
        }

        Map<String, Object> parameters = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        System.out.println("Request Received :"+query);
        ObjectMapper mapper = new ObjectMapper();
        Set<Accounts> accounts= new HashSet<>();
        MoneyTransferBook moneyTransferBook = mapper.readValue(query,MoneyTransferBook.class);
        System.out.println(moneyTransferBook.toString());

        //logic for payment transaction goes here

        //1. check for both creditor and debtor account exist
        ResultSet resultSet= DBConnectionFactory.getResultSet("select * from accounts where account_id="+moneyTransferBook.getCrAccount());
        DBEntityMapper<Accounts> dbEntityMapper= new AccountsRowMapper();
        try {
            accounts= dbEntityMapper.dbMapper(resultSet);
            Optional<Accounts> account=accounts.stream().findFirst();
           if(account.get().getBalance()>moneyTransferBook.getAmount()) {
               //deduct the money from the creditor
               float currentBal=account.get().getBalance() - moneyTransferBook.getAmount();
               account.get().setBalance(currentBal);

               //update payment transaction table
               PaymentTransaction paymentTransaction= new PaymentTransaction();
               paymentTransaction.setCr_account( moneyTransferBook.getCrAccount());
               paymentTransaction.setDr_account(moneyTransferBook.getDrAccount());
               paymentTransaction.setAmount(moneyTransferBook.getAmount());
               paymentTransaction.setCurrency("USD");
               paymentTransaction.setTranferType("BOOK");

               String insertPaymentTransaction="INSERT INTO payment_transaction (cr_account )VALUES("+"'"+moneyTransferBook.getCrAccount()+"'"+")";
                int i=DBConnectionFactory.extecuteStatment(insertPaymentTransaction);
               System.out.println(i);
           }else{
               httpResponse(httpExchange, "There are more than one accounts",400 ,parameters, mapper, moneyTransferBook);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //2. get current account balancex

        // send response
        //MoneyTransferBook moneyTransferBook= new MoneyTransferBook(true,"10","1",12.12F,false,null)
        httpResponse(httpExchange, moneyTransferBook.toString(),200 ,parameters, mapper, moneyTransferBook);

    }

    private void httpResponse(HttpExchange httpExchange, String message, int status, Map<String, Object> parameters, ObjectMapper mapper, MoneyTransferBook moneyTransferBook) throws IOException {
        String jsonInString = mapper.writeValueAsString(message);
        String response = jsonInString;
        for (String key : parameters.keySet())
            response += key + " = " + parameters.get(key) + "\n";
        httpExchange.sendResponseHeaders(status, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
