package com.scheduler.api.service;

import com.scheduler.api.entity.MeetingDto;
import com.scheduler.api.exception.ConflictException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();
    private static final String TIME_ONE = "2023-03-05 10:15";
    private static final String TIME_TWO = "2023-03-05 10:30";
    private static final String TIME_THREE = "2023-03-05 10:45";
    private static final List<String> PARTICIPANTS_OPTIMAL = List.of("gunda.gunda", "romeo.wilson");

    @Test
    void startTimeBeforeEndTime() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_THREE);
        meetingDto.setEndTime(TIME_TWO);
        meetingDto.setTimeZone("Asia/Kolkata");
        meetingDto.setParticipants(PARTICIPANTS_OPTIMAL);

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto));
    }
}
