
import com.sun.net.httpserver.HttpServer;
import controller.GetBankingLocationsController;
import controller.GetTransactionController;
import controller.PostBankLocationController;
import factory.DBConnectionFactory;


import java.io.*;
import java.net.InetSocketAddress;


public class MoneyTransferApp {

    static {

       DBConnectionFactory.createSchemaTable();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Started http server as url localhost:8080!");
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/bank-locations", new GetBankingLocationsController());
        server.createContext("/create/bank-locations",  new PostBankLocationController());
        server.createContext("/payment-transaction/",  new GetTransactionController());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
