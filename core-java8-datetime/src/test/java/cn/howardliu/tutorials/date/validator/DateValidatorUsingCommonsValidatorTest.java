package cn.howardliu.tutorials.date.validator;

import java.util.Locale;

import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
class DateValidatorUsingCommonsValidatorTest {
    @Test
    void isValid() {
        final DateValidator dateValidator = new DateValidatorUsingCommonsValidator("yyyy-MM-dd");

        Assertions.assertTrue(dateValidator.isValid("2021-02-28"));
        Assertions.assertFalse(dateValidator.isValid("2021-02-30"));
    }

    @Test
    void isValid2() {
        Assertions.assertTrue(GenericValidator.isDate("2021-02-30", Locale.CHINA));
    }
}
