package com.gymbuddy.backgymbuddy.admin.newsletter.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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

    private final String URI_PREFIX = NEWSLETTER_PREFIX;

    private final NewsletterService newsletterService;

    /**
     * 뉴스레터 구독 이메일 전체 조회
     */
    @GetMapping(URI_PREFIX + "/newsltterList")
    public ResponseEntity<List<Newsletter>> selectNewsletterList() {
        return createResponseEntity(true, newsletterService.findAll());
    }

    /**
     * 뉴스레터 구독 이메일 등록
     */
    @PostMapping(URI_PREFIX + "/newSubscribe")
    public ResponseEntity<Map<String, Object>> insertNewSubscribe(@RequestBody Newsletter newsletter) {
        log.info("뉴스레터 등록: {}", newsletter);
        Long id = newsletterService.save(newsletter);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("email", newsletter.getEmail());
        return createResponseEntity(true, result);
    }

    /**
     * 뉴스레터 구독 이메일 삭제
     */
    @DeleteMapping(URI_PREFIX + "/deleteSubscribe")
    public ResponseEntity<Newsletter> deleteNewsletter(@PathVariable("id") Long id) {
        log.info("웹 BI 삭제: {}", id);
        return createResponseEntity(true, newsletterService.delete(id));
    }
}
