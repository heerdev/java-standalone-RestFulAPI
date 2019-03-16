package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import factory.DBConnectionFactory;
import model.BankLocation;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestHandler {

   public static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static class GetBankLocation implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            if(!t.getRequestMethod().equalsIgnoreCase("get")){
                t.sendResponseHeaders(404, "invalid request".length());
                OutputStream os = t.getResponseBody();
                os.write("invalid request".toString().getBytes());
                os.close();
            }

            ObjectMapper mapper = new ObjectMapper();
            BankLocation bankLocation= new BankLocation();
            bankLocation.setBranchId(001);
            bankLocation.setAddress("New York City");
            //Object to JSON in String
            String jsonInString = mapper.writeValueAsString(bankLocation);
            t.sendResponseHeaders(200, jsonInString.length());
            OutputStream os = t.getResponseBody();
            os.write(jsonInString.getBytes());
            os.close();
        }
    }


   public static class PostBankLocation implements HttpHandler {

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

            ObjectMapper mapper = new ObjectMapper();


            BankLocation bankLocation = mapper.readValue(query,BankLocation.class);
            String insertBankLocation="INSERT INTO BANK_LOCATION VALUES("+bankLocation.getBranchId()+","+"'"+bankLocation.getAddress().trim().toString()+"'"+")";
            DBConnectionFactory.extecuteStatment(insertBankLocation);
            parseQuery(query, parameters);

            // send response
            String response = "";
            for (String key : parameters.keySet())
                response += key + " = " + parameters.get(key) + "\n";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }
    }

    public static void parseQuery(String query, Map<String,
            Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }
}
