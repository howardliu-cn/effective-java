package cn.howardliu.effectjava.rename;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-10-21
 */
public final class CookieFileReader {
    private CookieFileReader() {
    }

    public static List<String> read() throws IOException {
        final List<String> list = FileUtils.readLines(new File("./cookie.txt"), Charset.defaultCharset());
        if (CollectionUtils.isEmpty(list) || list.isEmpty()) {
            throw new IllegalArgumentException("未指定cookie信息，请在cookie.txt中指定cookie信息");
        }
        if (StringUtils.equals("COOKIE从控制台获取", list.get(0))) {
            throw new IllegalArgumentException("请在cookie.txt中指定cookie信息，cookie可以从控制台获取");
        }
        return list;
    }
}
