package cn.howardliu.tutorials.date.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
class DateValidatorUsingDateFormatTest {

    @Test
    void isValid() {
       final DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

        Assertions.assertTrue(validator.isValid("2021-02-28"));
        Assertions.assertFalse(validator.isValid("2021-02-30"));
    }
}
