package cn.howardliu.tutorials.openfeign;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Body;
import feign.Feign;
import feign.Headers;
import feign.Logger.Level;
import feign.Param;
import feign.RequestLine;
import feign.RetryableException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.spring.SpringContract;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-09-30
 */
public class OpenfeignTest {

    @Test
    public void testCore() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .target(Client.class, "https://httpbin.org");
        final String anything = client.anything("testCore");
        Assertions.assertNotNull(anything);
        Assertions.assertTrue(anything.contains("testCore"));
        System.out.println(anything);
    }

    @Test
    public void testJackson() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(Client.class, "https://httpbin.org");
        final Map<String, Object> requestBody = Map.of("k1", "value1", "k2", "value2");
        final Map<String, Object> anythingResult = client.anythingJson("testJson", requestBody);
        System.out.println(anythingResult);

        Assertions.assertNotNull(anythingResult);
        Assertions.assertTrue(anythingResult.get("url") instanceof String);
        Assertions.assertTrue(((String) anythingResult.get("url")).endsWith("testJson"));
        Assertions.assertTrue(anythingResult.containsKey("json"));
        Assertions.assertTrue(anythingResult.get("json") instanceof Map<?, ?>);
    }

    @Test
    public void testGson() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(Client.class, "https://httpbin.org");
        final Map<String, Object> requestBody = Map.of("k1", "value1", "k2", "value2");
        final Map<String, Object> anythingResult = client.anythingJson("testJson", requestBody);
        System.out.println(anythingResult);

        Assertions.assertNotNull(anythingResult);
        Assertions.assertTrue(anythingResult.get("url") instanceof String);
        Assertions.assertTrue(((String) anythingResult.get("url")).endsWith("testJson"));
        Assertions.assertTrue(anythingResult.containsKey("json"));
        Assertions.assertTrue(anythingResult.get("json") instanceof Map<?, ?>);
    }

    @Test
    public void testJsonBodyTemplate() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(Client.class, "https://httpbin.org");
        final Map<String, Object> anythingResult = client.anythingJsonBodyTemplate("testJson", "value1", "value2");
        System.out.println(anythingResult);

        Assertions.assertNotNull(anythingResult);
        Assertions.assertTrue(anythingResult.get("url") instanceof String);
        Assertions.assertTrue(((String) anythingResult.get("url")).endsWith("testJson"));
        Assertions.assertTrue(anythingResult.containsKey("json"));
        Assertions.assertTrue(anythingResult.get("json") instanceof Map<?, ?>);
    }

    @Test
    public void testOkHttpClient() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .client(new OkHttpClient())
                .target(Client.class, "https://httpbin.org");
        final Map<String, Object> anythingResult = client.anythingJsonBodyTemplate("testJson", "value1", "value2");
        System.out.println(anythingResult);

        Assertions.assertNotNull(anythingResult);
        Assertions.assertTrue(anythingResult.get("url") instanceof String);
        Assertions.assertTrue(((String) anythingResult.get("url")).endsWith("testJson"));
        Assertions.assertTrue(anythingResult.containsKey("json"));
        Assertions.assertTrue(anythingResult.get("json") instanceof Map<?, ?>);
    }

    @Test
    public void testInterceptor() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .requestInterceptor(new MyRequestInterceptor())
                .target(Client.class, "https://httpbin.org");
        final Map<String, Object> anythingResult = client.anythingJsonBodyTemplate("testJson", "value1", "value2");
        System.out.println(anythingResult);

        Assertions.assertNotNull(anythingResult);
        Assertions.assertTrue(anythingResult.get("url") instanceof String);
        Assertions.assertTrue(((String) anythingResult.get("url")).endsWith("testJson"));
        Assertions.assertTrue(anythingResult.containsKey("json"));
        Assertions.assertTrue(anythingResult.get("json") instanceof Map<?, ?>);

        Assertions.assertTrue(anythingResult.containsKey("headers"));
        boolean hasMyHeader = false;
        if (anythingResult.get("headers") instanceof Map headers) {
            for (Object key : headers.keySet()) {
                if (key.toString().equalsIgnoreCase("my-header")) {
                    hasMyHeader = true;
                    final Object value = headers.get(key);
                    Assertions.assertTrue(value instanceof String);
                    Assertions.assertEquals("my-value", value);
                }
            }
        }
        Assertions.assertTrue(hasMyHeader);
    }

    @Test
    public void testRetryer() {
        final Client client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .retryer(new MyRetryer())
                // 默认是 feign.Retryer.Default
                // 可以指定不重试 feign.Retryer.NEVER_RETRY
                .target(Client.class, "https://httpbin.abc");
        Assertions.assertThrowsExactly(RetryableException.class, () -> client.codes("500"));
    }

    @Test
    public void anythingJsonSpring() {
        final SpringClient client = Feign.builder()
                .logLevel(Level.FULL)
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .contract(new SpringContract())
                .target(SpringClient.class, "https://httpbin.org");
        final Map<String, Object> requestBody = Map.of("k1", "value1", "k2", "value2");
        final Map<String, Object> anythingResult = client.anythingJsonSpring("testJson",
                "param1", "param2", requestBody);
        System.out.println(anythingResult);

        Assertions.assertNotNull(anythingResult);
        Assertions.assertTrue(anythingResult.get("url") instanceof String);
        Assertions.assertTrue(((String) anythingResult.get("url")).contains("testJson"));
        Assertions.assertTrue(anythingResult.containsKey("json"));
        Assertions.assertTrue(anythingResult.get("json") instanceof Map<?, ?>);
    }

    private interface Client {
        @RequestLine("GET /anything/{anything}")
        @Headers({"Content-Type: application/json"})
        String anything(@Param("anything") String anything);

        @RequestLine("POST /anything/{anything}")
        @Headers({"Content-Type: application/json"})
        Map<String, Object> anythingJson(@Param("anything") String anything, Map<String, Object> requestBody);

        @RequestLine("POST /anything/{anything}")
        @Headers({"Content-Type: application/json"})
        @Body("%7B\"anything\": \"{anything}\", \"k1\": \"{v1}\", \"k2\": \"{v2}\"%7D")
        Map<String, Object> anythingJsonBodyTemplate(@Param("anything") String anything, @Param("v1") String v1,
                @Param("v2") String v2);

        @RequestLine("GET /status/{codes}")
        @Headers({"Content-Type: application/json"})
        String codes(@Param("codes") String codes);
    }

    private interface SpringClient {
        @PostMapping(value = "/anything/{anything}",
                produces = "application/json", consumes = "application/json")
        Map<String, Object> anythingJsonSpring(@PathVariable("anything") String anything,
                @RequestParam("p1") String p1,
                @RequestParam("p2") String p2,
                @RequestBody Map<String, Object> requestBody);
    }

}
