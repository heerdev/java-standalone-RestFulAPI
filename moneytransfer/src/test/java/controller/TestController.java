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
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.junit.Test;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestController {

    @Test
    public  void bookTransfer() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/book-transfer");

        String json = "{\"drAccount\":\"1112\",\"crAccount\":\"1111\",\"amount\":1,\"paymentDate\":null,\"scheduledPayment\":false,\"bookPayment\":true}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        String responseBody = EntityUtils.toString(response.getEntity());
        assertThat(responseBody, containsString("Transaction Success"));

        client.close();
    }
}
