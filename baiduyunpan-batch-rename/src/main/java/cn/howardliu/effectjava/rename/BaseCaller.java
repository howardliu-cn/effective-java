package cn.howardliu.effectjava.rename;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public abstract class BaseCaller {
    protected final String cookie;
    protected final Map<String, List<String>> headers = Maps.newHashMap();

    protected BaseCaller(String cookie, Map<String, List<String>> customHeaders) {
        this.cookie = cookie;
        initHeaders();
        this.headers.putAll(customHeaders);
    }

    private void initHeaders() {
        this.headers.put("sec-ch-ua", Collections.singletonList("\"Chromium\";v=\"94\","
                + " \"Google Chrome\";v=\"94\", \";Not A Brand\";v=\"99\""));
        this.headers.put("Accept", Collections.singletonList("application/json, text/javascript, */*; q=0.01"));
        this.headers.put("User-Agent", Collections.singletonList("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)"
                + " AppleWebKit/537.36 (KHTML, like Gecko)"
                + " Chrome/94.0.4606.81 Safari/537.36"));
        this.headers.put("sec-ch-ua-platform", Collections.singletonList("macOS"));
        this.headers.put("Sec-Fetch-Site", Collections.singletonList("same-origin"));
        this.headers.put("Sec-Fetch-Mode", Collections.singletonList("cors"));
        this.headers.put("Sec-Fetch-Dest", Collections.singletonList("empty"));
        this.headers.put("Referer", Collections.singletonList("https://pan.baidu.com/disk/home"));
        this.headers.put("Accept-Language", Collections.singletonList("zh-CN,zh;q=0.9"));
    }

}
