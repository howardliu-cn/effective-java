package cn.howardliu.tutorials.java14;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author 看山 <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2022/2/16 21:34
 */
class SwitchTest {
    @Test
    void testSwitch() {
        final DayOfWeek day = DayOfWeek.from(LocalDate.now());
        String typeOfDay = "";
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                typeOfDay = "Working Day";
                break;
            case SATURDAY:
            case SUNDAY:
                typeOfDay = "Rest Day";
                break;
        }

        Assertions.assertFalse(typeOfDay.isEmpty());
    }

    @Test
    void testSwitchExpression() {
        final DayOfWeek day = DayOfWeek.SATURDAY;
        final String typeOfDay = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                System.out.println("Working Day: " + day);
                yield "Working Day";
            }
            case SATURDAY, SUNDAY -> "Day Off";
        };

        Assertions.assertEquals("Day Off", typeOfDay);
    }
}
