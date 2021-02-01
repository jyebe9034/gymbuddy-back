package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service.FQService;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.FQ_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FQController extends BaseController {

    private final String URI_PREFIX = FQ_PREFIX;

    private final FQService fqService;

    /**
     * 전체 자주묻는질문 조회
     */
    @GetMapping(URI_PREFIX + "/all")
    public ResponseEntity<List<FrequencyQuestion>> selectFqList(@PathVariable("page") int page) {
        return createResponseEntity(true, fqService.findAll(page));
    }

    /**
     * 자주묻는질문 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<FrequencyQuestion> selectFqDetail(@PathVariable("id") Long id) {
        log.info("자주묻는질문 조회: {}", id);
        return createResponseEntity(true, fqService.findOne(id));
    }

    /**
     * 자주묻는질문 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertFq(@RequestBody FrequencyQuestion frequencyQuestion) {
        log.info("자주묻는질문 등록: {}", frequencyQuestion);
        Long id = fqService.save(frequencyQuestion);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 수정
     */
    @PostMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateFq(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("자주묻는질문 수정 - id: {}, param: {}", id, param);
        fqService.update(id, param);

        FrequencyQuestion findFq = fqService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", findFq.getId());
        result.put("title", findFq.getTitle());
        result.put("contents", findFq.getContents());
        return createResponseEntity(true, result);
    }

    /**
     * 자주묻는질문 삭제
     */
    @GetMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteFq(@RequestBody List<Integer> ids) {
        log.info("자주묻는질문 삭제: {}", ids);
        fqService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", 0);
        return createResponseEntity(true, result);
    }
}
