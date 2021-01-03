package com.gymbuddy.backgymbuddy.admin.businessIdentity.controller;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.service.BIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BIController {

    private final BIService biService;
}
