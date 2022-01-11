package cn.howardliu.tutorials.java12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/11 22:03
 */
class StringTest {
    @Test
    void testIndent() {
        final String text = "\t\t\t你好，我是看山。\n \u0020\u2005Java12的 新特性。\r欢迎三连+关注哟";
        assertEquals("    \t\t\t你好，我是看山。\n     \u0020\u2005Java12的 新特性。\n    欢迎三连+关注哟\n", text.indent(4));
        assertEquals("\t你好，我是看山。\n\u2005Java12的 新特性。\n欢迎三连+关注哟\n", text.indent(-2));

        final String text2 = "山水有相逢";
        assertEquals("山水有相逢", text2);
    }

    @Test
    void testTransform() {
        final String text = "看山是山";
        final String reverseText = text.transform(s -> new StringBuilder(s).reverse().toString());
        assertEquals("山是山看", reverseText);

        final String reverseText2 = Optional.of(text)
                .map(s -> new StringBuilder(s).reverse().toString())
                .orElse("");
        assertEquals("山是山看", reverseText2);
    }
}
