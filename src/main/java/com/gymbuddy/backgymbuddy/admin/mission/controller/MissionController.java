package com.gymbuddy.backgymbuddy.admin.mission.controller;

import com.gymbuddy.backgymbuddy.admin.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
}
