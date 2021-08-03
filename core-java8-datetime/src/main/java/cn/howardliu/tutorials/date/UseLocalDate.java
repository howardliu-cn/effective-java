package cn.howardliu.tutorials.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author kanshan <howardliu1988@163.com>
 * Created on 2021-06-11
 */
public class UseLocalDate {
    public static void main(String[] args) {
        LocalDate.of(2021, 6, 11);
        LocalDate.parse("2021-06-11");

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        DayOfWeek friday = LocalDate.parse("2021-06-11").getDayOfWeek();
        System.out.println(friday);

        int eleven = LocalDate.parse("2021-06-11").getDayOfMonth();
        System.out.println(eleven);

        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println(leapYear);

        boolean isBefore = LocalDate.parse("2021-06-11").isBefore(LocalDate.parse("2021-06-12"));
        System.out.println(isBefore);

        boolean isAfter = LocalDate.parse("2021-06-12").isAfter(LocalDate.parse("2021-06-11"));
        System.out.println(isAfter);

        LocalDateTime beginningOfDay = LocalDate.parse("2021-06-11").atStartOfDay();
        System.out.println(beginningOfDay);

        LocalDate firstDayOfMonth = LocalDate.parse("2021-06-11").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);

        System.out.println(LocalDate.MIN);
        System.out.println(LocalDate.MAX);

        LocalTime now = LocalTime.now();

        LocalTime sixty30 = LocalTime.parse("16:30");
        System.out.println(sixty30);
        LocalTime sixty302 = LocalTime.of(16, 30);
        System.out.println(sixty302);

        LocalTime seventy30 = LocalTime.parse("16:30").plus(1, ChronoUnit.HOURS);
        System.out.println(seventy30);

        int sixty = LocalTime.parse("16:30").getHour();
        System.out.println(sixty);

        boolean isBeforeTime = LocalTime.parse("16:30").isBefore(LocalTime.parse("17:30"));
        System.out.println(isBeforeTime);

        boolean isAfterTime = LocalTime.parse("17:30").isAfter(LocalTime.parse("16:30"));
        System.out.println(isAfterTime);

        System.out.println(LocalTime.MIN);
        System.out.println(LocalTime.NOON);
        System.out.println(LocalTime.MAX);
        System.out.println(LocalTime.MIDNIGHT);

        LocalDateTime.of(2021, Month.JUNE, 11, 16, 30);
        LocalDateTime.parse("2021-06-11T16:30:00");

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId);

        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.getDayOfMonth());
        System.out.println(zonedDateTime.getHour());
        System.out.println(zonedDateTime.getOffset());
        System.out.println(zonedDateTime.getZone());

        System.out.println(ZonedDateTime.now());
        System.out.println(ZonedDateTime.now(ZoneId.systemDefault()));
        final ZonedDateTime newyorkZonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(newyorkZonedDateTime);
        final ZonedDateTime shanghaiZonedDateTime =
                newyorkZonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        System.out.println(shanghaiZonedDateTime);
        final ZonedDateTime shanghaiZonedDateTime2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(shanghaiZonedDateTime2);

        Period period = Period.ZERO;
    }
}
