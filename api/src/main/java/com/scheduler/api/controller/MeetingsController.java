package com.scheduler.api.controller;

import com.scheduler.api.entity.MeetingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingsController {

    @PostMapping
    public ResponseEntity<Void> createMeeting(@RequestBody MeetingDto meetingDto) {

        return null;
    }
}
