package com.iron.rms.activity.controller;

import com.iron.rms.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActivityController {

    private ActivityService activityService;


}
