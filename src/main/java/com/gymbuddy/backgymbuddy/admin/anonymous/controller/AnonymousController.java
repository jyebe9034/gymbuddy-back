package com.gymbuddy.backgymbuddy.admin.anonymous.controller;

import com.gymbuddy.backgymbuddy.admin.anonymous.service.AnonymousService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor // final이 붙어있는 필드만을 가지고 생성자를 만들어 줌.
public class AnonymousController {

    private final AnonymousService anonymousService;
}
