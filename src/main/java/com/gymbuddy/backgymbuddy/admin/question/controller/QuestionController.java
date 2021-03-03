package com.gymbuddy.backgymbuddy.admin.question.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.question.domain.*;
import com.gymbuddy.backgymbuddy.admin.question.service.QuestionCommentService;
import com.gymbuddy.backgymbuddy.admin.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_QUESTION_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.USER_QUESTION_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuestionController extends BaseController {

    private String questionPath = "/resources/static/img/question";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + questionPath;
    private File saveFile = new File(rootPath);

    private final QuestionService questionService;
    private final QuestionCommentService questionCommentService;

    /**
     * 전체 1:1 문의글 조회(관리자)
     */
    @GetMapping(USER_QUESTION_PREFIX + "/all/{page}")
    public ResponseEntity<Map<String, Object>> selectAdminQuestionList(@PathVariable int page) {
        return createResponseEntity(true, questionService.findAll(page));
    }

    /**
     * 전체 문의글 갯수 조회
     */
    @GetMapping(USER_QUESTION_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectQuestionTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", questionService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 1:1 문의 상세 조회(관리자)
     */
    @GetMapping(ADMIN_QUESTION_PREFIX + "/detail/{id}")
    public ResponseEntity<Map<String, Object>> selectAdminQuestionDetail(@PathVariable("id") Long id) {
        log.info("1:1 문의 조회: {}", id);
        return createResponseEntity(true, questionService.findOneByDto(id));
    }

    /**
     * 1:1 문의 삭제(관리자)
     */
    @DeleteMapping(ADMIN_QUESTION_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteQuestionByAdmin(@RequestBody List<Integer> ids) {
        log.info("1:1 문의 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            questionService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

    /**
     * 전체 1:1 문의 조회(사용자)
     * 사용자가 쓴 문의 전체 조회
     */
    @GetMapping(USER_QUESTION_PREFIX + "/all/{createId}/{page}")
    public ResponseEntity<Map<String, Object>> selectUserQuestionList(
            @PathVariable("createId") String createId, @PathVariable("page") int page) {
        return createResponseEntity(true, questionService.findAllByUser(createId, page));
    }

    /**
     * 1:1 문의 상세 조회(사용자)
     */
    @GetMapping(USER_QUESTION_PREFIX + "/detail/{id}")
    public ResponseEntity<Map<String, Object>> selectUserQuestionDetail(@PathVariable("id") Long id) {
        log.info("1:1 문의 조회: {}", id);
        return createResponseEntity(true, questionService.findOneByDto(id));
    }

    /**
     * 1:1 문의 등록(사용자)
     */
    @PostMapping("/question/new")
    public ResponseEntity<Map<String, Object>> insertQuestion(@ModelAttribute QuestionDto dto) {
        log.info("1:1 문의 등록: {}", dto);

        String imgName1 = dto.getFile1().getOriginalFilename();
        String imgName2 = dto.getFile2().getOriginalFilename();
        String imgName3 = dto.getFile3().getOriginalFilename();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            // 파일1
            File realFile1 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName1);
            dto.getFile1().transferTo(realFile1);
            dto.setImgName1(imgName1);
            dto.setImgPath1(questionPath + "/" + realFile1.getName());

            // 파일2
            if (!dto.getFile2().isEmpty()) {
                File realFile2 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName2);
                dto.getFile2().transferTo(realFile2);
                dto.setImgName2(imgName2);
                dto.setImgPath2(questionPath + "/" + realFile2.getName());
            }

            // 파일3
            if (!dto.getFile3().isEmpty()) {
                File realFile3 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName3);
                dto.getFile3().transferTo(realFile3);
                dto.setImgName3(imgName3);
                dto.setImgPath3(questionPath + "/" + realFile3.getName());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", questionService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 1:1 문의 수정(사용자)
     */
    @PutMapping(USER_QUESTION_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateQuestion(
            @PathVariable("id") Long id, @ModelAttribute QuestionDto dto) {
        log.info("미션 수정 id: {}, dto: {}", id, dto);

        Question question = questionService.findOne(id);

        if (!dto.getFile1().isEmpty()) {
            String imgName1 = dto.getFile1().getOriginalFilename();
            if (!question.getImgName1().equals(imgName1)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName1);
                    dto.getFile1().transferTo(realFile);
                    dto.setImgName1(imgName1);
                    dto.setImgPath1(questionPath + "/" + realFile.getName());

                    File originFile = new File(saveFile + "/" + dto.getImgPath1());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        if (!dto.getFile2().isEmpty()) {
            String imgName2 = dto.getFile2().getOriginalFilename();
            if (!question.getImgName2().equals(imgName2)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName2);
                    dto.getFile2().transferTo(realFile);
                    dto.setImgName2(imgName2);
                    dto.setImgPath2(questionPath + "/" + realFile.getName());

                    File originFile = new File(saveFile + "/" + dto.getImgPath2());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        if (!dto.getFile3().isEmpty()) {
            String imgName3 = dto.getFile3().getOriginalFilename();
            if (!question.getImgName3().equals(imgName3)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName3);
                    dto.getFile3().transferTo(realFile);
                    dto.setImgName3(imgName3);
                    dto.setImgPath3(questionPath + "/" + realFile.getName());

                    File originFile = new File(saveFile + "/" + dto.getImgPath3());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        questionService.update(id, dto);
        Question findQuestion = questionService.findOne(id);

        boolean flag = true;
        if (dto.getContents() != null) {
            flag = dto.getContents().equals(findQuestion.getContents()) ? true : false;
        }
        if (dto.getImgPath1() != null) {
            flag = dto.getImgPath1().equals(findQuestion.getImgPath1()) ? true : false;
        }
        if (dto.getImgName1() != null) {
            flag = dto.getImgName1().equals(findQuestion.getImgName1()) ? true : false;
        }
        if (dto.getImgPath2() != null) {
            flag = dto.getImgPath2().equals(findQuestion.getImgPath2()) ? true : false;
        }
        if (dto.getImgName2() != null) {
            flag = dto.getImgName2().equals(findQuestion.getImgName2()) ? true : false;
        }
        if (dto.getImgPath3() != null) {
            flag = dto.getImgPath3().equals(findQuestion.getImgPath3()) ? true : false;
        }
        if (dto.getImgName3() != null) {
            flag = dto.getImgName3().equals(findQuestion.getImgName3()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 1:1 문의 삭제(사용자)
     * 상세 페이지에서만 삭제 가능
     */
    @DeleteMapping(USER_QUESTION_PREFIX + "/delete/{id}")
    public ResponseEntity<History> deleteQuestion(@PathVariable("id") Long id) {
        log.info("1:1 문의 삭제: {}", id);

        questionService.delete(id);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

    /**
     * 1:1 문의 댓글 등록(관리자)
     */
    @PostMapping(ADMIN_QUESTION_PREFIX + "/newReply/{id}")
    public ResponseEntity<QuestionComment> insertQuestionReply(
            @PathVariable("id") Long id, @RequestBody QuestionCommentDto dto) {
        log.info("1:1 문의 댓글 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", questionCommentService.save(id, dto));
        return createResponseEntity(true, result);
    }

    /**
     * 1:1 문의 댓글 수정(관리자)
     */
    @PutMapping(ADMIN_QUESTION_PREFIX + "/updateReply/{id}")
    public ResponseEntity<History> updateQuestionReply(
            @PathVariable("id") Long id, @RequestBody QuestionCommentDto dto) {
        log.info("1:1 문의 댓글 수정 id: {}, dto: {}", id, dto);

        questionCommentService.update(id, dto);
        QuestionComment findComment = questionCommentService.findOne(id);

        boolean flag = true;
        if (dto.getContents() != null) {
            flag = dto.getContents().equals(findComment.getContents()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 1:1 문의 댓글 삭제(관리자)
     */
    @DeleteMapping(ADMIN_QUESTION_PREFIX + "/deleteReply/{id}")
    public ResponseEntity<List<QuestionComment>> deleteQuestionReply(@PathVariable("id") Long id) {
        log.info("1:1 문의 댓글 삭제: {}", id);

        questionCommentService.delete(id);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

    /**
     * 문의글 검색(관리자)
     */
    @GetMapping(ADMIN_QUESTION_PREFIX + "/search/{page}")
    public ResponseEntity<List<Question>> searchQuestion(
            @RequestBody QuestionSearch search, @PathVariable("page") int page) {
        log.info("문의글 검색: {}", search);
        return createResponseEntity(true, questionService.search(search, page));
    }
}
