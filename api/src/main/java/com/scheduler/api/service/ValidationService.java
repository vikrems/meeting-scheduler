package com.scheduler.api.service;

import com.scheduler.api.entity.MeetingDto;
import com.scheduler.api.exception.ConflictException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ValidationService {

    public void validate(MeetingDto meetingDto) {
        ZoneId zoneId = ZoneId.of(meetingDto.getTimeZone());
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(zoneId);
        ZonedDateTime startTime = ZonedDateTime.parse(meetingDto.getStartTime(), FORMATTER);
        ZonedDateTime endTime = ZonedDateTime.parse(meetingDto.getEndTime(), FORMATTER);
        if (startTime.isAfter(endTime))
            throw new ConflictException("Start time should be before end time");
    }

}
