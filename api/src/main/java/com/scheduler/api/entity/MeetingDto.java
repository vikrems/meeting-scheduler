package com.scheduler.api.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class MeetingDto {

    private String meetingId;

    private String startTime;

    private String endTime;

    private String timeZone;

    private List<String> participants;
}
