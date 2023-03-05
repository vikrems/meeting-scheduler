package com.scheduler.api.service;

import com.scheduler.api.entity.MeetingDto;
import com.scheduler.api.exception.ConflictException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.scheduler.api.util.ApiConstants.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();
    private static final String TIME_ZONE = "Asia/Kolkata";
    private static final String TIME_ONE = "2023-03-05 10:15";
    private static final String TIME_TWO = "2023-03-05 10:30";
    private static final String TIME_INCORRECT = "2023-03-05 10:20";
    private static final List<String> PARTICIPANTS_OPTIMAL = List.of("gunda.gunda", "romeo.wilson");

    @Test
    void allInputValid() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_ONE);
        meetingDto.setEndTime(TIME_TWO);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(PARTICIPANTS_OPTIMAL);

        assertDoesNotThrow(() -> validationService.validate(meetingDto));
    }

    @Test
    void startTimeBeforeEndTime() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_TWO);
        meetingDto.setEndTime(TIME_ONE);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(PARTICIPANTS_OPTIMAL);

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto),
                START_BEFORE_END);
    }

    @Test
    void startTimeAtEndTime() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_TWO);
        meetingDto.setEndTime(TIME_TWO);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(PARTICIPANTS_OPTIMAL);

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto),
                START_BEFORE_END);
    }

    @Test
    void startTimeInCorrectTimeSlice() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_INCORRECT);
        meetingDto.setEndTime(TIME_TWO);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(PARTICIPANTS_OPTIMAL);

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto),
                INCORRECT_TIME_SLICE);
    }

    @Test
    void endTimeInCorrectTimeSlice() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_ONE);
        meetingDto.setEndTime(TIME_INCORRECT);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(PARTICIPANTS_OPTIMAL);

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto),
                INCORRECT_TIME_SLICE);
    }

    @Test
    void minParticipantThresholdNotMet() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_ONE);
        meetingDto.setEndTime(TIME_INCORRECT);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(List.of("vikshe"));

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto),
                PARTICIPANT_COUNT);
    }

    @Test
    void maxParticipantThresholdNotMet() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setStartTime(TIME_ONE);
        meetingDto.setEndTime(TIME_INCORRECT);
        meetingDto.setTimeZone(TIME_ZONE);
        meetingDto.setParticipants(List.of("vikshe", "nari", "karthik", "kamal", "murali",
                "pugal"));

        assertThrows(ConflictException.class, () -> validationService.validate(meetingDto),
                PARTICIPANT_COUNT);
    }
}
