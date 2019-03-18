package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Accounts;
import model.MoneyTransferBook;
import org.codehaus.jackson.map.ObjectMapper;
import service.PaymentTransactionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
i
import java.util.*;

public class PostBookTransferController implements HttpHandler {

    private  PaymentTransactionService paymentTransactionService;


    public PostBookTransferController(){
        this.paymentTransactionService= new PaymentTransactionService();
    }

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


        boolean isBookPymtSuccessful =paymentTransactionService.booktTransfer( moneyTransferBook);
        if(isBookPymtSuccessful){
            httpResponse(httpExchange, moneyTransferBook.toString(),200 ,parameters, mapper, moneyTransferBook);
        }else{
            httpResponse(httpExchange, "Money Transfer Unsuccesfull".toString(),400 ,parameters, mapper, moneyTransferBook);
        }




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
