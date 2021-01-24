package com.gymbuddy.backgymbuddy.admin.question.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import com.gymbuddy.backgymbuddy.admin.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.OpenMBeanConstructorInfo;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuestionController extends BaseController {

    private final QuestionService questionService;

    /**
     * 전체 1:1문의 조회
     */
    @GetMapping("/questionList")
    public List<Question> selectQuestionList(@RequestParam Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 상세 조회
     */
    @GetMapping("/questionDetail/{no}")
    public Question selectQuestionDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 1:1문의 삭제(관리자)
     */
    @GetMapping("/deleteQuestionByAdmin")
    public Map<String, Object> deleteQuestionByAdmin(@RequestParam Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 등록(사용자)
     */
    @PostMapping("/newQuestion")
    public Map<String, Object> insertQuestion(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 수정(사용자)
     */
    @PostMapping("/updateQuestion")
    public Map<String, Object> updateQuestion(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 삭제(사용자)
     */
    @PostMapping("/deleteQuestion")
    public Map<String, Object> deleteQuestion(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 댓글 등록(관리자)
     */
    @PostMapping("/questionReply")
    public Map<String, Object> insertQuestionReply(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 댓글 수정(관리자)
     */
    @PostMapping("/updateQuestionReply")
    public Map<String, Object> updateQuestionReply(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 1:1문의 댓글 삭제(관리자)
     */
    @PostMapping("/deleteQuestionReply")
    public Map<String, Object> deleteQuestionReply(@RequestBody Map<String, Object> param) {
        return null;
    }
}
