package com.scheduler.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {

    public static final int MIN_PARTICIPANT_COUNT = 2;
    public static final int MAX_PARTICIPANT_COUNT = 5;

    public static final String START_BEFORE_END = "Start time should be before end time";
    public static final String INCORRECT_TIME_SLICE = "Start and end time minute should be in the 15th minute sector," +
            " ie : (0, 15, 30 or 45)";
    public static final String PARTICIPANT_COUNT = "Number of participants should be between " + MIN_PARTICIPANT_COUNT +
            " and " + MAX_PARTICIPANT_COUNT;
}
