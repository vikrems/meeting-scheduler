package com.scheduler.api.service;

import com.scheduler.api.entity.MeetingDto;
import com.scheduler.api.exception.ConflictException;
import com.scheduler.api.util.ApiConstants;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.scheduler.api.util.ApiConstants.*;

@Service
public class ValidationService {

    private static final int TIME_SLICE = 15;

    public void validate(MeetingDto meetingDto) {
        ZoneId zoneId = ZoneId.of(meetingDto.getTimeZone());
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .withZone(zoneId);
        ZonedDateTime startTime = ZonedDateTime.parse(meetingDto.getStartTime(), FORMATTER);
        ZonedDateTime endTime = ZonedDateTime.parse(meetingDto.getEndTime(), FORMATTER);
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime))
            throw new ConflictException(START_BEFORE_END);

        if (doesNotMeetTimeSlice(startTime) || doesNotMeetTimeSlice(endTime))
            throw new ConflictException(INCORRECT_TIME_SLICE);

        int participantsSize = meetingDto.getParticipants().size();
        if (participantsSize < MIN_PARTICIPANT_COUNT || participantsSize > MAX_PARTICIPANT_COUNT)
            throw new ConflictException(ApiConstants.PARTICIPANT_COUNT);
    }

    private boolean doesNotMeetTimeSlice(ZonedDateTime time) {
        return time.getMinute() % TIME_SLICE != 0;
    }
}
