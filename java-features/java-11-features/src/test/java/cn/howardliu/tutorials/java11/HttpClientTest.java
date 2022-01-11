package cn.howardliu.tutorials.java11;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/8 15:02
 */
class HttpClientTest {
    @Test
    void testHttpClient() throws IOException, InterruptedException {
        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.howardliu.cn/robots.txt"))
                .build();
        final HttpResponse<String> httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
        final String responseBody = httpResponse.body();
        assertTrue(responseBody.contains("Allow"));
    }
}
