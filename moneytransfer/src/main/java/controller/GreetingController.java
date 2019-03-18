package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.MoneyTransferWireICDT;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class GreetingController implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {

        Map<String, Object> parameters = new HashMap<>();



        MoneyTransferWireICDT moneyTransferWireICDT= new MoneyTransferWireICDT(true,"1111","XSDC",12f);
        String response = moneyTransferWireICDT.toString();
        for (String key : parameters.keySet())
            response += key + " = " + parameters.get(key) + "\n";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
