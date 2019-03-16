import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import factory.DBProvider;
import java.io.*;
import java.net.InetSocketAddress;


public class MoneyTransferApp {


    static{
       new DBProvider.DBProviderBuilder().getConnection().extecuteStatment().closeConnection().build();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Started http server as url localhost:8080!");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
