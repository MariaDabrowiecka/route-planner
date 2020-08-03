package com.dabrowiecka.routeplanner.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DurationCalculator {

    private DurationCalculator() {
    }

    public static long durationInMinutes(Date from, Date to) {
        long durationInMilliseconds = Math.abs(to.getTime() - from.getTime());
        return TimeUnit.MINUTES.convert(durationInMilliseconds, TimeUnit.MILLISECONDS);
    }

}
