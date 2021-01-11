package com.gymbuddy.backgymbuddy.admin.review.controller;

import com.gymbuddy.backgymbuddy.admin.review.domain.Review;
import com.gymbuddy.backgymbuddy.admin.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 전체 후기 조회
     */
    @GetMapping("/reviewList")
    public List<Review> selectReviewList(@RequestParam Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 상세 조회
     */
    @GetMapping("/reviewDetail/{no}")
    public Review selectReviewDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 메인에 넣을 후기 설정
     */
    @PostMapping("/updateReviewForMain")
    public Map<String, Object> updateReviewForMain(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 등록(사용자)
     */
    @PostMapping("/newReview")
    public Map<String, Object> insertReview(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 수정(사용자)
     */
    @PostMapping("/updateReview")
    public Map<String, Object> updateReview(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 삭제(사용자)
     */
    @DeleteMapping("/deleteReview")
    public Map<String, Object> deleteReview(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 댓글 등록(관리자)
     */
    @PostMapping("/reviewReply")
    public Map<String, Object> insertReviewReply(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 댓글 수정(관리자)
     */
    @PostMapping("/updateReviewReply")
    public Map<String, Object> updateReviewReply(@RequestBody Map<String, Object> param) {
        return null;
    }

    /**
     * 후기 댓글 삭제
     */
    @PostMapping("/deleteReviewReply")
    public Map<String, Object> deleteReviewReply(@RequestBody Map<String, Object> param) {
        return null;
    }


}
