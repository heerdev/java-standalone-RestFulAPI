package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import factory.DBConnectionFactory;
import model.BankLocation;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostBankLocationController implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {

        if(!he.getRequestMethod().equalsIgnoreCase("post")){
            he.sendResponseHeaders(404, "invalid request".length());
            OutputStream os = he.getResponseBody();
            os.write("invalid request".toString().getBytes());
            os.close();
        }


        Map<String, Object> parameters = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();

        int rowCount=saveBankLocation(query);

        if(rowCount==0){
            he.sendResponseHeaders(409, "Conflict".length());
            OutputStream os = he.getResponseBody();
            os.write("record already present".toString().getBytes());
            os.close();
        }else if (rowCount==1){
            he.sendResponseHeaders(202, "create".length());
            OutputStream os = he.getResponseBody();
            os.write("entiry saved".toString().getBytes());
            os.close();
        }
        // send response
        he.sendResponseHeaders(400, "BAD REQUEST".length());
        OutputStream os = he.getResponseBody();
        os.write("NOT ALLOWED".toString().getBytes());
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
