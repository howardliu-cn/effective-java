package cn.howardliu.tutorials.date.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
class DateValidatorUsingDateTimeFormatterTest {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.CHINA);

    @Test
    void isValid() {
        final DateTimeFormatter dateFormatter = DATE_FORMATTER.withResolverStyle(ResolverStyle.STRICT);
        final DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        Assertions.assertTrue(validator.isValid("2021-02-28"));
        Assertions.assertFalse(validator.isValid("2021-02-30"));
    }

    @Test
    void testResolverStyle() {
        Assertions.assertEquals(LocalDate.of(2021, 2,28), parseDate("2021-02-28", ResolverStyle.STRICT));
        Assertions.assertNull(parseDate("2021-02-29", ResolverStyle.STRICT));

        Assertions.assertEquals(LocalDate.of(2021, 2,28), parseDate("2021-02-28", ResolverStyle.STRICT));
        Assertions.assertNull(parseDate("2021-13-28", ResolverStyle.STRICT));

        Assertions.assertEquals(LocalDate.of(2021, 2,28), parseDate("2021-02-28", ResolverStyle.SMART));
        Assertions.assertEquals(LocalDate.of(2021, 2,28), parseDate("2021-02-29", ResolverStyle.SMART));

        Assertions.assertNull(parseDate("2021-13-28", ResolverStyle.SMART));
        Assertions.assertNull(parseDate("2021-13-29", ResolverStyle.SMART));

        Assertions.assertEquals(LocalDate.of(2021, 2,28), parseDate("2021-02-28", ResolverStyle.LENIENT));
        Assertions.assertEquals(LocalDate.of(2021, 3,1), parseDate("2021-02-29", ResolverStyle.LENIENT));

        Assertions.assertEquals(LocalDate.of(2022, 1,28), parseDate("2021-13-28", ResolverStyle.LENIENT));
        Assertions.assertEquals(LocalDate.of(2022, 2,2), parseDate("2021-13-33", ResolverStyle.LENIENT));
    }

    private static LocalDate parseDate(String dateString, ResolverStyle resolverStyle) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER.withResolverStyle(resolverStyle));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
