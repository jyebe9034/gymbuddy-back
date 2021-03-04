package com.gymbuddy.backgymbuddy.admin.newsletter.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.NewsletterDto;
import com.gymbuddy.backgymbuddy.admin.newsletter.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.NEWSLETTER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsletterController extends BaseController {

    private final NewsletterService newsletterService;

    /**
     * 뉴스레터 구독 이메일 전체 조회
     */
    @GetMapping(NEWSLETTER_PREFIX + "/all/{page}")
    public ResponseEntity<List<Newsletter>> selectSubscriberList(@PathVariable int page) {
        return createResponseEntity(true, newsletterService.findAll(page));
    }

    /**
     * 뉴스레터 구독 이메일 등록
     */
    @PostMapping(NEWSLETTER_PREFIX + "/newSubscribe")
    public ResponseEntity<Map<String, Object>> insertNewSubscribe(@RequestBody NewsletterDto dto) {
        log.info("뉴스레터 구독 이메일 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", newsletterService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 뉴스레터 구독 이메일 삭제
     */
    @DeleteMapping(NEWSLETTER_PREFIX + "/deleteSubscribe/{id}")
    public ResponseEntity<Newsletter> deleteNewsletter(@PathVariable("id") Long id) {
        log.info("뉴스레터 구독 이메일 삭제: {}", id);

        newsletterService.delete(id);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

    /**
     * 뉴스레터 구독일자 검색
     */
    @GetMapping(NEWSLETTER_PREFIX + "/search/{start}/{end}")
    public ResponseEntity<List<Newsletter>> searchCreateDate(
            @PathVariable("start") String start,@PathVariable("end") String end) {
        log.info("뉴스레터 구독일자 검색 start: {}, end: {}", start, end);
        return createResponseEntity(true, newsletterService.search(start, end));
    }
}
