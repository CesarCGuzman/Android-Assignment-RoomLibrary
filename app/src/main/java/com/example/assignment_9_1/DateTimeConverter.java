package com.example.assignment_9_1;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class DateTimeConverter {
    /*
       A good chunk of this code was created with the help of ChatGPT so we will comment it to show our understanding of it.
       This is needed because the room database cannot store a calendar object,
       so we need to convert it to and from a long because apparently room can store longs but not calendars
     */

    // This converts a long to a calendar object
    @TypeConverter
    public static Calendar toCalendar(Long timestamp) {
        // If the timestamp is null, return null
        if (timestamp == null) {
            return null;
        }

        // Create a new calendar object
        Calendar calendar = Calendar.getInstance();

        // Set the time of the calendar object to the timestamp
        calendar.setTimeInMillis(timestamp);

        // Return the calendar object
        return calendar;
    }

    // This converts a calendar object to a long
    @TypeConverter
    public static Long toTimestamp(Calendar calendar) {
        // If the calendar is null, return null
        if (calendar == null) {
            return null;
        }

        // Return the time in milliseconds of the calendar object
        return calendar.getTimeInMillis();
    }
}
