package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Accounts;
import model.MoneyTransferWireICDT;
import model.MoneyTransferWireRCDT;
import org.codehaus.jackson.map.ObjectMapper;
import service.PaymentTransactionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PostWireSendingTransferController implements HttpHandler {

    private PaymentTransactionService paymentTransactionService;


    public PostWireSendingTransferController(){
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
        MoneyTransferWireICDT moneyTransferWireICDT = mapper.readValue(query, MoneyTransferWireICDT.class);
        System.out.println(moneyTransferWireICDT.toString());


        boolean isICDTPymtSuccessful =paymentTransactionService.wireTransferICDT( moneyTransferWireICDT);
        if(isICDTPymtSuccessful){
            httpResponse(httpExchange, "Transaction Success!!".toString(),200 ,parameters, mapper, moneyTransferWireICDT);
        }else{
            httpResponse(httpExchange, "Transaction Unsuccesfull".toString(),400 ,parameters, mapper, moneyTransferWireICDT);
        }
    }


    private void httpResponse(HttpExchange httpExchange, String message, int status, Map<String, Object> parameters, ObjectMapper mapper, MoneyTransferWireICDT moneyTransferWireICDT) throws IOException {
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
