package cn.howardliu.tutorials.date.validator;

import org.apache.commons.validator.GenericValidator;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
public class DateValidatorUsingCommonsValidator implements DateValidator {
    private final String dateFormat;

    public DateValidatorUsingCommonsValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String dateStr) {
        return GenericValidator.isDate(dateStr, dateFormat, true);
    }
}
