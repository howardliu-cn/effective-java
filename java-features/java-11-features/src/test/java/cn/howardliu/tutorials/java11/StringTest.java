package cn.howardliu.tutorials.java11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/6 22:44
 */
class StringTest {
    @Test
    void testRepeat() {
        final String output = "foo ".repeat(2) + "bar";
        assertEquals("foo foo bar", output);
    }

    @Test
    void testTrip() {
        final String output = "\n\t  hello   \u2005".strip();
        assertEquals("hello", output);

        final String trimOutput = "\n\t  hello   \u2005".trim();
        assertEquals("hello   \u2005", trimOutput);
    }

    @Test
    void testTripLeading() {
        final String output = "\n\t  hello   \u2005".stripLeading();
        assertEquals("hello   \u2005", output);
    }

    @Test
    void testTripTrailing() {
        final String output = "\n\t  hello   \u2005".stripTrailing();
        assertEquals("\n\t  hello", output);
    }

    @Test
    void testIsBlank() {
        assertTrue("\n\t\u2005".isBlank());
    }

    @Test
    void testLines() {
        final String multiline = "This is\n \na multiline\nstring.";
        final String output = multiline.lines()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.joining(" "));
        assertEquals("This is a multiline string.", output);
    }
}
