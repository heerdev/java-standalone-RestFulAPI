package controller;

import com.google.gson.Gson;
import factory.DBConnectionFactory;
import javafx.geometry.Pos;
import model.MoneyTransferBook;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestController {
    CloseableHttpClient client;
    HttpPost httpPost;

    @Before
    public void init(){
        client  = HttpClients.createDefault();

    }


    @Test
    public  void bookTransferTest() throws IOException {

        httpPost = new HttpPost("http://localhost:8080/book-transfer");
        String json = "{\"drAccount\":\"1112\",\"crAccount\":\"1111\",\"amount\":1,\"paymentDate\":null,\"scheduledPayment\":false,\"bookPayment\":true}";
        assertSuccess(json, httpPost, client);

        client.close();
    }

    @Test
    public  void wireRCDTTransferTest() throws IOException {
        httpPost = new HttpPost("http://localhost:8080/wire-transfer/rcdt");

        String json = "{\"beneficiaryAccount\":\"1111\",\"benificiaryBank\":\"CHASE\",\"scheuledDate\":null,\"senderRef\":\"SSSS\",\"amount\":100.0,\"wirePayment\":true,\"scheduledPayment\":false}";
        assertSuccess(json, httpPost, client);

        client.close();
    }

    @Test
    public  void wireICDTTransferTest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/wire-transfer/icdt");

        String json = "{\"debtorAccount\":\"1111\",\"bic\":\"xxxx\",\"amount\":12.0,\"wirePayment\":true}";
        assertSuccess(json, httpPost, client);

        client.close();
    }


    private void assertSuccess(String json, HttpPost httpPost, CloseableHttpClient client) throws IOException {
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        String responseBody = EntityUtils.toString(response.getEntity());
        assertThat(responseBody, containsString("Transaction Success"));
    }

}
