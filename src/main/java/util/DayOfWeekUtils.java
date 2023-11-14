package util;

import java.time.LocalDate;

public class DayOfWeekUtils {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;

    public static boolean isWeekday(int visitDate) {
        int dayOfWeekNumber = getDayOfWeekNumber(visitDate);
        return dayOfWeekNumber != FRIDAY && dayOfWeekNumber != SATURDAY;
    }

    public static boolean isWeekend(int visitDate) {
        int dayOfWeekNumber = getDayOfWeekNumber(visitDate);
        return dayOfWeekNumber == FRIDAY || dayOfWeekNumber == SATURDAY;
    }

    private static int getDayOfWeekNumber(int visitDate) {
        LocalDate date = LocalDate.of(YEAR, MONTH, visitDate);
        return date.getDayOfWeek().getValue();
    }
}