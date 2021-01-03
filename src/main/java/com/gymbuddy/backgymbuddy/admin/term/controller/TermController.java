package com.gymbuddy.backgymbuddy.admin.term.controller;

import com.gymbuddy.backgymbuddy.admin.term.service.TermService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;
}
