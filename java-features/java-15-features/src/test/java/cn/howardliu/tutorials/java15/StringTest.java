package cn.howardliu.tutorials.java15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/2/16 21:34
 */
class StringTest {

    @Test
    void testTextBlock() {
        final String singleLine = "你好，我是看山，公众号「看山的小屋」。这行没有换行，而且我的后面多了一个空格 \n这次换行了";
        final String textBlockSingleLine = """
                你好，我是看山，公众号「看山的小屋」。\
                这行没有换行，而且我的后面多了一个空格\s
                这次换行了""";

        Assertions.assertEquals(singleLine, textBlockSingleLine);
    }
}
