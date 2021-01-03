package com.gymbuddy.backgymbuddy.admin.mainBanner.controller;

import com.gymbuddy.backgymbuddy.admin.mainBanner.service.MBService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MBController {

    private final MBService mbService;
}
