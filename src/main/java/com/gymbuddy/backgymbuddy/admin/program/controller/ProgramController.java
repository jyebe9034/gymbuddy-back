package com.gymbuddy.backgymbuddy.admin.program.controller;

import com.gymbuddy.backgymbuddy.admin.program.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;
}
