package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.MoneyTransferWireICDT;
import model.MoneyTransferWireRCDT;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class GreetingController implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {

        Map<String, Object> parameters = new HashMap<>();



        MoneyTransferWireICDT moneyTransferWireICDT= new MoneyTransferWireICDT(true,"1111","xxxx",12F);
        ObjectMapper mapper = new ObjectMapper();

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(moneyTransferWireICDT);
        t.sendResponseHeaders(200, jsonInString.length());
        OutputStream os = t.getResponseBody();
        os.write(jsonInString.getBytes());
        os.close();
    }
}
