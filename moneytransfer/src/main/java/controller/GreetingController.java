package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;


public class GreetingController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.sendResponseHeaders(200, "HI there!!".length());
        OutputStream os = httpExchange.getResponseBody();
        os.write("HI there!".getBytes());
        os.close();
    }
}
