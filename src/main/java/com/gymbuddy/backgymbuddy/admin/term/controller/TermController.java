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

import static com.gymbuddy.backgymbuddy.admin.base.Constants.TERM_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TermController extends BaseController {

    private final String URI_PREFIX = TERM_PREFIX;

    private final TermService termService;

    /**
     * 전체 약관 조회 (관리자)
     */
    @GetMapping(URI_PREFIX + "/all")
    public ResponseEntity<List<Term>> selectTermList() {
        return createResponseEntity(true, termService.findAll());
    }

    /**
     * 약관 상세 (관리자)
     */
    @GetMapping(URI_PREFIX + "/detail/{title}")
    public ResponseEntity<Term> findByTitle(@PathVariable("title") String title) {
        return createResponseEntity(true, termService.findByTitle(title));
    }

    /**
     * 푸터 - 개인정보처리방침 보기 (사용자)
     */
    @GetMapping(URI_PREFIX + "/footer/private_policy")
    public ResponseEntity<Term> selectPrivacyPolicy() {
        return createResponseEntity(true, termService.findPrivatePolicy("개인정보 처리 방침"));
    }

    /**
     * 푸터 - 이용약관 보기 (사용자)
     */
    @GetMapping(URI_PREFIX + "/footer/term_of_use")
    public ResponseEntity<Term> selectTermOfUser() {
        return createResponseEntity(true, termService.findTermsOfUse("이용약관"));
    }

    /**
     * 약관 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertTerm(@RequestBody Term term) {
        log.info("약관 등록: {}", term);
        Long id = termService.save(term);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 약관 (이미지) 수정(삭제 & 등록)
     */
    @PutMapping(URI_PREFIX + "/update")
    public ResponseEntity<Map<String, Object>> updateTerm(
            @PathVariable("id") Long id, @RequestBody Map<String, Object> param) {
        log.info("약관 수정 id: {}, param: {}", param);
        termService.update(id, param);

        Term findTerm = termService.findOne(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("title", findTerm.getTitle());
        return createResponseEntity(true, result);
    }
}
