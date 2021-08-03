package cn.howardliu.tutorials.date.validator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
public class DateValidatorUsingDateTimeFormatter implements DateValidator {
    private final DateTimeFormatter dateFormatter;

    public DateValidatorUsingDateTimeFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            this.dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
