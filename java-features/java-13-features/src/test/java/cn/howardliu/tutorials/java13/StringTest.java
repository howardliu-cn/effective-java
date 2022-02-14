package cn.howardliu.tutorials.java13;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/2/12 23:03
 */
class StringTest {

    @Test
    void testTextBlock() {
        String json = "{\n"
                + "  \"wechat\": \"hellokanshan\",\n"
                + "  \"wechatName\": \"看山\",\n"
                + "  \"mp\": \"kanshanshuo\",\n"
                + "  \"mpName\": \"看山的小屋\"\n"
                + "}\n";

        String json2 = """
                {
                  "wechat": "hellokanshan",
                  "wechatName": "看山",
                  "mp": "kanshanshuo",
                  "mpName": "看山的小屋"
                }
                """;

        System.out.println(json);
        System.out.println(json2);
    }
}
