package cn.howardliu.tutorials.java12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/1/12 23:59
 */
class CompactNumberFormatTest {
    @Test
    void testFormat() {
        final NumberFormat zhShort = NumberFormat.getCompactNumberInstance(Locale.CHINA, Style.SHORT);
        assertEquals("1万", zhShort.format(10_000));
        assertEquals("1兆", zhShort.format(1L << 40));

        final NumberFormat zhLong = NumberFormat.getCompactNumberInstance(Locale.CHINA, Style.LONG);
        assertEquals("1万", zhLong.format(10_000));
        assertEquals("1兆", zhLong.format(1L << 40));

        final NumberFormat usShort = NumberFormat.getCompactNumberInstance(Locale.US, Style.SHORT);
        usShort.setMaximumFractionDigits(2);
        assertEquals("10K", usShort.format(10_000));
        assertEquals("1.1T", usShort.format(1L << 40));

        final NumberFormat usLong = NumberFormat.getCompactNumberInstance(Locale.US, Style.LONG);
        usLong.setMaximumFractionDigits(2);
        assertEquals("10 thousand", usLong.format(10_000));
        assertEquals("1.1 trillion", usLong.format(1L << 40));
    }
}
