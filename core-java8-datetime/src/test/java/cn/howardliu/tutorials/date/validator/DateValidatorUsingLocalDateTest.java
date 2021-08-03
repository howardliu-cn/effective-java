package cn.howardliu.tutorials.date.validator;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
class DateValidatorUsingLocalDateTest {

    @Test
    void isValid() {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        final DateValidator validator = new DateValidatorUsingLocalDate(dateFormatter);

        Assertions.assertTrue(validator.isValid("2021-02-28"));
        Assertions.assertFalse(validator.isValid("2021-02-30"));
    }
}
