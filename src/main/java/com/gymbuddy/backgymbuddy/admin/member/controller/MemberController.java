package com.gymbuddy.backgymbuddy.admin.member.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import com.gymbuddy.backgymbuddy.admin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController extends BaseController {

    private final MemberService memberService;

    /**
     * 웹 멤버소개 조회
     */
    @GetMapping("/WebMember")
    public List<Member> selectMemberBi() {
        return null;
    }

    /**
     * 웹 멤버소개 등록(이미지 1개)
     */
    @PostMapping("/newWebMember")
    public Map<String, Object> insertWebMember(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 웹 멤버소개 삭제
     */
    @GetMapping("/deleteWebMember/{no}")
    public Map<String, Object> deleteWebMember(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 모바일 멤버소개 조회
     */
    @GetMapping("/MobildMember")
    public List<Member> selectMobileMember() {
        return null;
    }

    /**
     * 모바일 멤버소개 등록(이미지 1개)
     */
    @PostMapping("/newMobileMember")
    public Map<String, Object> insertMobileMember(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 모바일 멤버소개 삭제
     */
    @GetMapping("/deleteMobileMember/{no}")
    public Map<String, Object> deleteMobileMember(@PathVariable("no") int no) {
        return null;
    }

}
