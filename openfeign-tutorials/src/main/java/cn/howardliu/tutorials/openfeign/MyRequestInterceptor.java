package cn.howardliu.tutorials.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-10-08
 */
public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("my-header", "my-value");
    }
}
