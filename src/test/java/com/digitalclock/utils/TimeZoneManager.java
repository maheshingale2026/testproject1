package com.digitalclock.utils;

import com.digitalclock.models.TimeZoneData;
import java.util.ArrayList;
import java.util.List;

public class TimeZoneManager {
    private static List<TimeZoneData> defaultTimeZones = new ArrayList<>();

    static {
        // Initialize default time zones
        defaultTimeZones.add(new TimeZoneData("UTC", "UTC+0"));
        defaultTimeZones.add(new TimeZoneData("New York", "UTC-5"));
        defaultTimeZones.add(new TimeZoneData("London", "UTC+0"));
        defaultTimeZones.add(new TimeZoneData("Tokyo", "UTC+9"));
        defaultTimeZones.add(new TimeZoneData("Sydney", "UTC+10"));
    }

    /**
     * Gets the default list of time zones
     */
    public static List<TimeZoneData> getDefaultTimeZones() {
        return new ArrayList<>(defaultTimeZones);
    }

    /**
     * Gets a specific time zone by name
     */
    public static TimeZoneData getTimeZone(String name) {
        for (TimeZoneData tz : defaultTimeZones) {
            if (tz.getTimezoneName().equals(name)) {
                return tz;
            }
        }
        return null;
    }

    /**
     * Adds a new time zone
     */
    public static void addTimeZone(TimeZoneData timeZone) {
        if (timeZone != null) {
            defaultTimeZones.add(timeZone);
        }
    }

    /**
     * Removes a time zone
     */
    public static void removeTimeZone(TimeZoneData timeZone) {
        if (timeZone != null) {
            defaultTimeZones.remove(timeZone);
        }
    }
}
