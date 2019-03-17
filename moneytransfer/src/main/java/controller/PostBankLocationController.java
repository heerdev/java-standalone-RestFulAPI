package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import factory.DBConnectionFactory;
import model.BankLocation;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PostBankLocationController implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {

        if(!he.getRequestMethod().equalsIgnoreCase("post")){
            he.sendResponseHeaders(404, "invalid request".length());
            OutputStream os = he.getResponseBody();
            os.write("invalid request".getBytes());
            os.close();
        }


        Map<String, Object> parameters = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        int httpCode=0;
        String responsBodyText=null;

        int rowCount=saveBankLocation(query);

        if(rowCount==0){
            httpCode=409;
            responsBodyText="COULD NOT SAVE THE ENTITY";
        }else if (rowCount==1){
            httpCode=202;
            responsBodyText="Entity Saved successfully";
        }
        // send response
        String response = responsBodyText;
        for (String key : parameters.keySet())
            response += key + " = " + parameters.get(key) + "\n";
        he.sendResponseHeaders(httpCode, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    private int saveBankLocation(String query) throws IOException {
        System.out.println("Save the Bank Location entiry into DB");
        ObjectMapper mapper = new ObjectMapper();
        BankLocation bankLocation = mapper.readValue(query,BankLocation.class);
        String insertBankLocation="INSERT INTO BANK_LOCATION VALUES("+bankLocation.getBranchId()+","+"'"+bankLocation.getAddress().trim().toString()+"'"+")";
        return DBConnectionFactory.extecuteStatment(insertBankLocation);
    }


}
