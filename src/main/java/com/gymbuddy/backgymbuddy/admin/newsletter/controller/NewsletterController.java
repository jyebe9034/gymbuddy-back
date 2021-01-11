package com.gymbuddy.backgymbuddy.admin.newsletter.controller;

import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsletterController {

    private final NewsletterService newsletterService;

    /**
     * 뉴스레터 구독 이메일 전체 조회
     */
    @GetMapping("/newsLetterList")
    public List<Newsletter> selectNewsLetterList() {
        return null;
    }

    /**
     * 뉴스레터 구독 이메일 등록
     */
    @PostMapping("/newSubscribe")
    public Map<String, Object> insertNewSubscribe(@RequestBody Map<String, Object> param) {
        return null;
    }
}
