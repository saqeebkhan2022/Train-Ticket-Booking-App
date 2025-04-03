package com.booking.TRAIN_SERVICE.uitls;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtility {
    public static String getDayOfWeek(String date) {
        // Parse the date to a LocalDate object (input format should be 'yyyy-MM-dd')
        LocalDate localDate = LocalDate.parse(date);

        // Get the day of the week (e.g., Mon, Tue, Wed, etc.)
        return localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH); // "Mon", "Tue", etc.
    }
}
