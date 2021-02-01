package com.gymbuddy.backgymbuddy.admin.member.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import com.gymbuddy.backgymbuddy.admin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.MEMBER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController extends BaseController {

    private final String URI_PREFIX = MEMBER_PREFIX;
    private final MemberService memberService;

    /**
     * 웹 멤버소개 조회
     */
    @GetMapping(URI_PREFIX + "/web")
    public ResponseEntity<List<Member>> selectWebMember() {
        return createResponseEntity(true, memberService.findByStatus(WebMobileStatus.WEB));
    }

    /**
     * 모바일 멤버소개 조회
     */
    @GetMapping(URI_PREFIX + "/mobile")
    public ResponseEntity<List<Member>> selectMobileMember() {
        return createResponseEntity(true, memberService.findByStatus(WebMobileStatus.MOBILE));
    }

    /**
     * 멤버소개 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMember(@RequestBody Member member) {
        log.info("멤버소개 등록: {}", member);
        Long id = memberService.save(member);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * 멤버소개 삭제
     */
    @GetMapping(URI_PREFIX + "/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("id") Long id) {
        log.info("멤버소개 삭제: {}", id);
        memberService.delete(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

}
