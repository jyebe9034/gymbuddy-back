package com.gymbuddy.backgymbuddy.admin.notice.controller;

import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 전체 공지사항 조회(관리자)
     */
    @GetMapping("/allNotice")
    public List<Notice> selectNoticeList() {
        return null;
    }

    /**
     * 메인 노출 공지사항 조회
     */
    @GetMapping("/mainNotice")
    public List<Notice> selectMainNoticeList() {
        return null;
    }

    /**
     * 공지사항 상세
     */
    @GetMapping("/noticeDetail/{no}")
    public Map<String, Object> selectNoticeDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 공지사항 등록
     */
    @PostMapping("/newNotice")
    public Map<String, Object> insertNotice(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 공지사항 수정(메인 노출 유무 설정 포함)
     */
    @PostMapping("/updateNotice")
    public Map<String, Object> updateNotice(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 공지사항 삭제
     * @param no 삭제할 글번호
     */
    @GetMapping("/deleteNotice/{no}")
    public Map<String, Object> deleteNotice(@PathVariable("no") int no) {
        return null;
    }

}
