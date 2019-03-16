
import com.sun.net.httpserver.HttpServer;
import factory.DBConnectionFactory;
import server.HttpRequestHandler;


import java.io.*;
import java.net.InetSocketAddress;
import java.sql.Connection;


public class MoneyTransferApp {

    static {

        DBConnectionFactory.createSchemaTable();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Started http server as url localhost:8080!");


        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/test", new HttpRequestHandler.MyHandler());
        server.createContext("/bank-locations", new HttpRequestHandler.GetBankLocation());
        server.createContext("/create/bank-locations",  new HttpRequestHandler.PostBankLocation());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
