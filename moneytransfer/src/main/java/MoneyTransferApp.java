
import com.sun.net.httpserver.HttpServer;
import controller.*;
import factory.DBConnectionFactory;


import java.io.*;
import java.net.InetSocketAddress;


public class MoneyTransferApp {

    static {

       DBConnectionFactory.createSchemaTable();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\\n----------------------------------------------------------\\n\\t\" +\n" +
                "                \"Application '{}' is running! Access URLs:http://localhost:8080" );
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/hello",  new GreetingController());
        server.createContext("/book-transfer",  new PostBookTransferController());
        server.createContext("/wire-transfer/rcdt",  new PostWireReceiveTransferController());
        server.createContext("/wire-transfer/icdt",  new PostWireSendingTransferController());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
