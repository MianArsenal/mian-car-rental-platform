package com.mian.car.rental.util;

import javafx.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String TIME_STRING_FORMAT = "yyyy-MM-dd HH:mm";
    public static final long NUMBER_OF_MILLISECONDS_PER_HOUR = 60 * 60 * 1000;

    public static SimpleDateFormat getTimeStringFormat() {
        return new SimpleDateFormat(TIME_STRING_FORMAT);
    }

    public static Date getDate(String timeString) throws ParseException {
        return getTimeStringFormat().parse(timeString);
    }

    public static long getMilliSeconds(String timeString) throws ParseException {
        return getTimeStringFormat().parse(timeString).getTime();
    }

    public static List<Pair<String, String>> getTimeSlicePairs(String startTime, String endTime) throws ParseException {
        List<Pair<String, String>> result = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = getTimeStringFormat();
        long start = simpleDateFormat.parse(startTime).getTime();
        long end = simpleDateFormat.parse(endTime).getTime();
        for (long current = start + NUMBER_OF_MILLISECONDS_PER_HOUR; current <= end; current += NUMBER_OF_MILLISECONDS_PER_HOUR) {
            String subEndTimeString = simpleDateFormat.format(new Date(current));
            String subStartTimeString = simpleDateFormat.format(new Date(current - NUMBER_OF_MILLISECONDS_PER_HOUR));
            result.add(new Pair<>(subStartTimeString, subEndTimeString));
        }
        return result;
    }
}
