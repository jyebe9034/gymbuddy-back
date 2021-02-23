package com.gymbuddy.backgymbuddy.admin.term.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.domain.TermDto;
import com.gymbuddy.backgymbuddy.admin.term.service.TermService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.TERM_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TermController extends BaseController {

    private String termPath = "/resources/static/img/term";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + termPath;
    private File saveFile = new File(rootPath);

    private final TermService termService;

    /**
     * 전체 약관 조회 (관리자)
     */
    @GetMapping(TERM_PREFIX + "/all")
    public ResponseEntity<List<Term>> selectTermList() {
        return createResponseEntity(true, termService.findAll());
    }

    /**
     * 약관 상세 (관리자)
     */
    @GetMapping(TERM_PREFIX + "/detail/{title}")
    public ResponseEntity<Map<String, Object>> selectTermDetail(@PathVariable("title") String title) {
        return createResponseEntity(true, termService.findByTitle(title));
    }

    /**
     * 푸터 - 개인정보처리방침 보기 (사용자)
     */
    @GetMapping(TERM_PREFIX + "/footer/private_policy")
    public ResponseEntity<List<Term>> selectPrivatePolicy() {
        return createResponseEntity(true, termService.findPrivatePolicy());
    }

    /**
     * 푸터 - 이용약관 보기 (사용자)
     */
    @GetMapping(TERM_PREFIX + "/footer/term_of_use")
    public ResponseEntity<List<Term>> selectTermOfUser() {
        return createResponseEntity(true, termService.findTermsOfUse());
    }

    /**
     * 약관 등록
     */
    @PostMapping(TERM_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertTerm(@ModelAttribute TermDto term) {
        log.info("약관 등록: {}", term);

        String imgName = term.getFile().getOriginalFilename();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
            term.getFile().transferTo(realFile);
            term.setImgName(imgName);
            term.setImgPath(termPath + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", termService.save(term));
        return createResponseEntity(true, result);
    }

    /**
     * 약관 (이미지) 수정(삭제 & 등록)
     */
    @PutMapping(TERM_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateTerm(
            @PathVariable("id") Long id, @ModelAttribute TermDto dto) {
        log.info("약관 수정 id: {}, dto: {}", dto);

        Term term = termService.findOne(id);

        if (dto.getFile() != null) {
            String imgName = dto.getFile().getOriginalFilename();
            if (!term.getImgName().equals(imgName)) {
                try {
                    // 이미지 업로드
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
                    dto.getFile().transferTo(realFile);
                    dto.setImgName(imgName);
                    dto.setImgPath(termPath + realFile.getName());

                    // 기존 이미지를 파일 서버에서 삭제
                    File originFile = new File(saveFile + "/" + term.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        termService.update(id, dto);
        Term findTerm = termService.findOne(id);

        boolean flag = true;
        if (dto.getTitle() != null) {
            flag = dto.getTitle().equals(findTerm.getTitle());
        }
        if (dto.getImgPath() != null) {
            flag = dto.getImgPath().equals(findTerm.getImgPath());
        }
        if (dto.getImgName() != null) {
            flag = dto.getImgName().equals(findTerm.getImgName());
        }
        if (dto.getWebMobile() != null) {
            flag = dto.getWebMobile().equals(findTerm.getWebMobile());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
