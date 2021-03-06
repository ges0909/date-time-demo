/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package schrader.datetime.test;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeTest {

    private final static LocalDate NOW = LocalDate.now();

    /*
     * Manipulate LocalDateTime.
     */

    @Test
    public void addDayAndConvertLocalDateTime2Date() {
        LocalDateTime tomorrow = NOW.plusDays(1).atTime(12, 0, 0);
        Date.from(tomorrow.atZone(ZoneId.systemDefault()).toInstant()); // convert to 'Date'
    }

    @Test
    public void subtractHoursAndMinutes() {
        LocalDateTime today = LocalDateTime.now();
        today.minusHours(5).minusMinutes(30);
    }

    @Test
    public void firstDayOfMonth() {
        LocalDate date = LocalDate.of(2018, Month.FEBRUARY, 22); // 22.02.2018
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        assertThat(firstDayOfMonth.getDayOfMonth()).isEqualTo(1);
        assertThat(firstDayOfMonth.getMonth()).isEqualTo(Month.FEBRUARY);
        assertThat(firstDayOfMonth.getYear()).isEqualTo(2018);
    }

    @Test
    public void lastDayOfMonth() {
        LocalDate date = LocalDate.of(2018, Month.FEBRUARY, 22); // 22.02.2018
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        assertThat(lastDayOfMonth.getDayOfMonth()).isEqualTo(28);
        assertThat(lastDayOfMonth.getMonth()).isEqualTo(Month.FEBRUARY);
        assertThat(lastDayOfMonth.getYear()).isEqualTo(2018);
    }

    /*
     * Parse String to LocalDate, LocalTime and LocalDateTime.
     */

    @Test
    public void parseLocalDate() {
        String dateString = "09/09/1961";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        assertThat(localDate.getDayOfMonth()).isEqualTo(9);
        assertThat(localDate.getMonth()).isEqualTo(Month.SEPTEMBER);
        assertThat(localDate.getYear()).isEqualTo(1961);
    }

    @Test
    public void parseLocalTime() {
        String timeString = "23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(timeString, formatter);
        assertThat(localTime.getHour()).isEqualTo(23);
        assertThat(localTime.getMinute()).isEqualTo(59);
        assertThat(localTime.getSecond()).isEqualTo(59);
    }

    @Test
    public void parseLocalDateTime() {
        String dateString = "09/09/1961 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        assertThat(localDateTime.getDayOfMonth()).isEqualTo(9);
        assertThat(localDateTime.getMonth()).isEqualTo(Month.SEPTEMBER);
        assertThat(localDateTime.getYear()).isEqualTo(1961);
        assertThat(localDateTime.getHour()).isEqualTo(23);
        assertThat(localDateTime.getMinute()).isEqualTo(59);
        assertThat(localDateTime.getSecond()).isEqualTo(59);
    }

    @Test
    public void isAfter() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        assertThat(tomorrow.isAfter(today)).isTrue();
    }

    @Test
    public void isBefore() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        assertThat(today.isBefore(tomorrow)).isTrue();
    }

    @Test
    public void isEqual() {
        LocalDate today = LocalDate.now();
        assertThat(today.isEqual(today)).isTrue();
    }

    @Test
    public void getFirstDayOfMonth() {
        LocalDate date = LocalDate.of(2018, Month.FEBRUARY, 22);
        int dayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()).getDayOfMonth();
        assertThat(dayOfMonth).isEqualTo(1);
    }

    @Test
    public void getLastDayOfMonth() {
        LocalDate date = LocalDate.of(2018, Month.FEBRUARY, 22);
        int dayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        assertThat(dayOfMonth).isEqualTo(28);
    }

    @Test
    public void atStartOfDay() {
        LocalDate localDate = LocalDate.of(1961, Month.SEPTEMBER, 9);
        LocalDateTime atStartOfDay = localDate.atStartOfDay();
        assertThat(atStartOfDay.toString()).isEqualTo("1961-09-09T00:00");
    }

    @Test
    public void atEndOfDay() {
        LocalDate localDate = LocalDate.of(1961, Month.SEPTEMBER, 9);
        LocalDateTime atStartOfDay = localDate.atStartOfDay();
        LocalDateTime atEndOfDay = atStartOfDay.with(LocalTime.MAX);
        assertThat(atEndOfDay.toString()).isEqualTo("1961-09-09T23:59:59.999999999");
    }

    @Test
    public void getMonthOfDate() {
        LocalDateTime localDateTime = LocalDate.of(1961, Month.SEPTEMBER, 9).atStartOfDay();
        assertThat(localDateTime.getMonth()).isEqualTo(Month.SEPTEMBER);
        assertThat(localDateTime.getMonth().ordinal()).isEqualTo(9 - 1);
        assertThat(localDateTime.getMonthValue()).isEqualTo(9);
    }

    /**
     * Calculations with time periods.
     */

    @Test
    public void currentYear() {
        LocalDate startDate = NOW.withDayOfYear(1);
        LocalDate endDate = NOW;
    }

    @Test
    public void lastYear() {
        LocalDate startDate = NOW.minusYears(1).withDayOfYear(1);
        LocalDate endDate = NOW.minusYears(1).withDayOfYear(365);
    }

    @Test
    public void last12Months() {
        LocalDate startDate = NOW.minusMonths(12);
        LocalDate endDate = NOW;
    }

    @Test
    public void last3Years() {
        LocalDate startDate = NOW.minusYears(3).withDayOfYear(1);
        LocalDate endDate = NOW.minusYears(1).withDayOfYear(365);
    }

    @Test
    public void currentMonth() {
        LocalDate startDate = NOW.withDayOfMonth(1);
        LocalDate endDate = NOW;
    }

    @Test
    public void lastMonth() {
        LocalDate startDate = NOW.minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = NOW.minusMonths(1).withDayOfMonth(28);
    }

    @Test
    public void last15Days() {
        LocalDate yesterday = NOW.minusDays(1);
        LocalDate startDate = yesterday.minusDays(15);
        LocalDate endDate = yesterday;
    }

    @Test
    public void currentAndLastYear() {
        LocalDate startDate = NOW.minusYears(1).withDayOfYear(1);
        LocalDate endDate = NOW;
    }

    @Test
    public void currentAndLast11Months() {
        LocalDate startDate = NOW.minusMonths(11);
        LocalDate endDate = NOW;
    }

    @Test
    public void currentAndLast3Years() {
        LocalDate startDate = NOW.minusYears(3).withDayOfYear(1);
        LocalDate endDate = NOW;
    }

    @Test
    public void currentAndLast() {
        LocalDate startDate = NOW.minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = NOW;
    }

    @Test
    public void currentAndLast15Days() {
        LocalDate startDate = NOW.minusDays(1L + 5);
        LocalDate endDate = NOW;
    }
}
/**/