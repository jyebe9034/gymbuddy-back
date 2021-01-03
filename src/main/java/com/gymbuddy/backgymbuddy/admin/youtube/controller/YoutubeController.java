package com.gymbuddy.backgymbuddy.admin.youtube.controller;

import com.gymbuddy.backgymbuddy.admin.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeController {

    private final YoutubeService youtubeService;
}
