package com.gymbuddy.backgymbuddy.admin.column.controller;

import com.gymbuddy.backgymbuddy.admin.column.service.ColumnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;
}
