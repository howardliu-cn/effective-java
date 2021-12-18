package cn.howardliu.tutorials.java9;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * HowardLiu <howardliu1988@163.com>
 * Created on 2021/12/16 22:56
 */
public class HttpRequestMain {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        final String url = "https://postman-echo.com/get";
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        final HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        final HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
