package com.gymbuddy.backgymbuddy.admin.question.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.base.DirMake;
import com.gymbuddy.backgymbuddy.admin.enums.category.QuestionEnum;
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

    private String questionPath = "/resources/images/question";
    private String rootPath = "/home/www" + questionPath;
    private File saveFile = DirMake.testdir(rootPath);

    private final QuestionService questionService;
    private final QuestionCommentService questionCommentService;

    /**
     * 전체 1:1 문의글 조회(관리자)
     */
    @GetMapping(ADMIN_QUESTION_PREFIX + "/all/{page}")
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
    public ResponseEntity<QuestionDto> selectAdminQuestionDetail(@PathVariable("id") Long id) {
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

            Question findQuestion = questionService.findOne(idL);
            if (findQuestion.getImgPath1() != null) {
                File file = new File(findQuestion.getImgPath1());
                if (file.exists()) {
                    file.delete();
                }
            }
            if (findQuestion.getImgPath2() != null) {
                File file = new File(findQuestion.getImgPath2());
                if (file.exists()) {
                    file.delete();
                }
            }
            if (findQuestion.getImgPath3() != null) {
                File file = new File(findQuestion.getImgPath3());
                if (file.exists()) {
                    file.delete();
                }
            }
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
        return createResponseEntity(true, questionService.findOneByDto(id));
    }

    /**
     * 1:1 문의 등록(사용자)
     */
    @PostMapping(USER_QUESTION_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertQuestion(@ModelAttribute QuestionDto dto) {
        log.info("1:1 문의 등록: {}", dto);

        // 파일1
        if (dto.getFile1() != null) {
            String imgName1 = dto.getFile1().getOriginalFilename();
            try {
                if (!saveFile.exists()) {
                    try {
                        saveFile.mkdir();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                File realFile1 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName1);
                dto.getFile1().transferTo(realFile1);
                dto.setImgName1(realFile1.getName());
                dto.setImgPath1(questionPath + "/" + realFile1.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        // 파일2
        if (dto.getFile2() != null) {
            String imgName2 = dto.getFile2().getOriginalFilename();
            try {
                if (!saveFile.exists()) {
                    try {
                        saveFile.mkdir();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                File realFile2 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName2);
                dto.getFile1().transferTo(realFile2);
                dto.setImgName2(realFile2.getName());
                dto.setImgPath1(questionPath + "/" + realFile2.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        // 파일3
        if (dto.getFile3() != null) {
            String imgName3 = dto.getFile3().getOriginalFilename();
            try {
                if (!saveFile.exists()) {
                    try {
                        saveFile.mkdir();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                File realFile3 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName3);
                dto.getFile3().transferTo(realFile3);
                dto.setImgName3(realFile3.getName());
                dto.setImgPath3(questionPath + "/" + realFile3.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
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
        log.info("1:1 문의 수정 id: {}, dto: {}", id, dto);

        Question question = questionService.findOne(id);

        if (dto.getFile1() != null) {
            String imgName1 = dto.getFile1().getOriginalFilename();
            try {
                File realFile1 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName1);
                dto.getFile1().transferTo(realFile1);
                dto.setImgName1(realFile1.getName());
                dto.setImgPath1(questionPath + "/" + realFile1.getName());

                if (question.getImgPath1() != null) {
                    File originFile = new File(question.getImgPath1());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getFile2() != null) {
            String imgName2 = dto.getFile2().getOriginalFilename();
            try {
                    File realFile2 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName2);
                    dto.getFile2().transferTo(realFile2);
                    dto.setImgName2(realFile2.getName());
                    dto.setImgPath2(questionPath + "/" + realFile2.getName());

                if (question.getImgPath2() != null) {
                    File originFile = new File(question.getImgPath2());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getFile3() != null) {
            String imgName3 = dto.getFile3().getOriginalFilename();
            try {
                    File realFile3 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName3);
                    dto.getFile3().transferTo(realFile3);
                    dto.setImgName3(realFile3.getName());
                    dto.setImgPath3(questionPath + "/" + realFile3.getName());

                if (question.getImgPath3() != null) {
                    File originFile = new File(question.getImgPath3());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
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

        Question findQuestion = questionService.findOne(id);

        if (findQuestion.getImgPath1() != null) {
            File file = new File(findQuestion.getImgPath1());
            if (file.exists()) {
                file.delete();
            }
        }
        if (findQuestion.getImgPath2() != null) {
            File file = new File(findQuestion.getImgPath2());
            if (file.exists()) {
                file.delete();
            }
        }
        if (findQuestion.getImgPath3() != null) {
            File file = new File(findQuestion.getImgPath3());
            if (file.exists()) {
                file.delete();
            }
        }
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
    public ResponseEntity<List<QuestionDto>> searchQuestion(
            @PathVariable("page") int page, @RequestParam QuestionEnum categoryId,
            @RequestParam String keyword, @RequestParam String type) {
        log.info("문의글 검색 - categoryId: {}, keyword: {}, type: {}", categoryId, keyword, type);
        return createResponseEntity(true, questionService.search(categoryId, keyword, type, page));
    }
}
