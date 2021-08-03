package cn.howardliu.tutorials.date.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-08-03
 */
public class DateValidatorUsingDateFormat implements DateValidator {
    private final String dateFormat;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String dateStr) {
        final DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
