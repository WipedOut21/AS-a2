package pranoy.uni.project.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class with helper methods for Date arithmetics.
 */
public class DateUtils {

    /**
     * Helper function obtained from <a href="https://www.baeldung.com/java-months-difference-two-dates">http://baeldung.com</a>
     */
    public static int monthsBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Both startDate and endDate must be provided");
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        int startDateTotalMonths = 12 * startCalendar.get(Calendar.YEAR)
                + startCalendar.get(Calendar.MONTH);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int endDateTotalMonths = 12 * endCalendar.get(Calendar.YEAR)
                + endCalendar.get(Calendar.MONTH);

        return endDateTotalMonths - startDateTotalMonths;
    }
}
