package com.gymbuddy.backgymbuddy.admin.term.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.service.TermService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.TERM_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TermController extends BaseController {

    private final String URI_PREFIX = TERM_PREFIX;

    private final TermService termService;

    /**
     * 전체 이용약관 조회
     */
    @GetMapping(URI_PREFIX + "/allTerm")
    public ResponseEntity<List<Term>> selectTermList() {
        return createResponseEntity(true, termService.findALl());
    }

    /**
     * 웹 이용약관 상세
     */
    @GetMapping(URI_PREFIX + "/termDetail/{id}")
    public ResponseEntity<Term> selectTermDetail(@PathVariable("id") Long id) {
        log.info("약관 아이디 및 웹모바일여부로 조회: {}", id);
        return createResponseEntity(true, termService.findOne(id));
    }

    /**
     * 웹 이용약관 등록
     */
    @PostMapping(URI_PREFIX + "/newWebTerm")
    public ResponseEntity<Map<String, Object>> insertWebTerm(@RequestBody Term term) {
        log.info("약관 등록: {}", term);
        Long id = termService.save(term);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 웹 이용약관 수정
     */
    @PutMapping(URI_PREFIX + "/updateTerm/{id}")
    public ResponseEntity<Map<String, Object>> updateWebTerm(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("약관 수정 id: {}, param: {}", id, param);
        String title = Objects.toString(param.get("title"));
        termService.update(id, title);

        Term findTerm = termService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", findTerm.getId());
        result.put("title", findTerm.getTitle());
        return createResponseEntity(true, result);
    }

    /**
     * 웹 이용약관 삭제
     */
    @DeleteMapping(URI_PREFIX + "/deleteWebTerm")
    public ResponseEntity<Map<String, Object>> deleteWebTerm(@RequestParam List<Long> ids) {
        log.info("웹 이용약관 삭제: {}", ids.toString());
        int deleteResult = termService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", deleteResult);
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 이용약관 상세
     */
    @GetMapping(URI_PREFIX + "/mobileTermDetail/{id}")
    public ResponseEntity<Term> selectMobileTermDetail(@PathVariable("id") Long id) {
        log.info("약관 아이디 및 웹모바일여부로 조회: {}", id);
        return createResponseEntity(true, termService.findOne(id));
    }

    /**
     * 모바일 이용약관 등록
     */
    @PostMapping(URI_PREFIX + "/newMobileTerm")
    public ResponseEntity<Map<String, Object>> insertMobileTerm(@RequestBody Term term) {
        log.info("약관 등록(수정): {}", term);
        Long id = termService.save(term);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 이용약관 수정
     */
    @PutMapping(URI_PREFIX + "/updateMobileTerm/{id}")
    public ResponseEntity<Map<String, Object>> updateMobileTerm(@PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("약관 수정 id: {}, param: {}", id, param);
        String title = Objects.toString(param.get("title"));
        termService.update(id, title);

        Term findTerm = termService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", findTerm.getId());
        result.put("title", findTerm.getTitle());
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 이용약관 삭제
     */
    @GetMapping(URI_PREFIX + "/deleteMobileTerm")
    public ResponseEntity<Map<String, Object>> deleteMobileTerm(@RequestParam List<Long> ids) {
        log.info("웹 이용약관 삭제: {}", ids.toString());
        int deleteResult = termService.delete(ids);

        Map<String, Object> result = new HashMap<>();
        result.put("result", deleteResult);
        return createResponseEntity(true, result);
    }
}
