package cn.howardliu.tutorials.date.validator;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
class DateValidatorUsingDateTimeFormatterTest {

    @Test
    void isValid() {
        final DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd", Locale.CHINA)
                .withResolverStyle(ResolverStyle.STRICT);
        final DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        Assertions.assertTrue(validator.isValid("2021-02-28"));
        Assertions.assertFalse(validator.isValid("2021-02-30"));
    }
}
