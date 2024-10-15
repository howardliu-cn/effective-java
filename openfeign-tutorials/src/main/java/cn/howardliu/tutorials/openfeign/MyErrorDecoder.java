package cn.howardliu.tutorials.openfeign;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-10
 */
public class MyErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new RuntimeException("Bad Request");
            case 401 -> new RetryableException(response.status(),
                    response.reason(),
                    response.request().httpMethod(),
                    (Long) null,
                    response.request());
            case 404 -> new RuntimeException("Not found");
            case 500 -> new RuntimeException("Internal Server Error");
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }
}
