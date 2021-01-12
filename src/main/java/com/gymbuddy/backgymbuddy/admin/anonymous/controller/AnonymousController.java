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

   // 어느 시점에 비회원의 회원아이디를 생성해 주느냐에 대한 문제가 있음..
   // 장바구니에 넣으려는 상황에서 즉석에서 아이디를 발급해서 넣어주는게 가능할지..?

}
