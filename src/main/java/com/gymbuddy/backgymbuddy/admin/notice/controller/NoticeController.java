package com.gymbuddy.backgymbuddy.admin.notice.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.domain.NoticeDto;
import com.gymbuddy.backgymbuddy.admin.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.NOTICE_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController extends BaseController {

    private final String URI_PREFIX = NOTICE_PREFIX;
    private String noticePath = "/resources/static/img/notice";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + noticePath;
    private File newfile = new File(rootPath);

    private final NoticeService noticeService;

    /**
     * 전체 공지사항 조회(관리자)
     */
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<Notice>> selectNoticeList(@PathVariable("page") int page) {
        return createResponseEntity(true, noticeService.findAll(page));
    }

    /**
     * 메인 노출 공지사항 조회(최근 5개만)
     */
    @GetMapping(URI_PREFIX + "/mainAll")
    public ResponseEntity<List<Notice>> selectMainNoticeList() {
        return createResponseEntity(true, noticeService.findAllForMain());
    }

    /**
     * 공지사항 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<Notice> selectNoticeDetail(@PathVariable("id") Long id) {
        log.info("공지사항 아이디로 조회: {}", id);
        return createResponseEntity(true, noticeService.findOne(id));
    }

    /**
     * 공지사항 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertNotice(@RequestBody NoticeDto notice) {
        log.info("공지사항 등록: {}", notice);

        // 이미지 업로드
        String filename = notice.getFile().getOriginalFilename();
        try {
            if (!newfile.exists()) {
                newfile.mkdir();
            }
            File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + filename);
            notice.getFile().transferTo(realFile);
            notice.setImgName(filename);
            notice.setImgPath(noticePath + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", noticeService.save(notice));
        return createResponseEntity(true, result);
    }

    /**
     * 공지사항의 제목, 내용, 이미지, 메인 노출 여부 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateNotice(@PathVariable("id") Long id, @RequestBody NoticeDto notice) {
        log.info("공지사항 수정 - id: {}, param: {}", id, notice);

        Notice origin = noticeService.findOne(id);
        String filename = notice.getFile().getOriginalFilename();
        if (!origin.getImgName().equals(filename)) {
            // 이미지 업로드
            try {
                File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + filename);
                notice.getFile().transferTo(realFile);
                notice.setImgName(filename);
                notice.setImgPath(noticePath + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                File originFile = new File(newfile + "/" + origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        noticeService.update(id, notice);
        Notice findNotice = noticeService.findOne(id);

        boolean flag = true;
        if (notice.getTitle() != null) {
            flag = notice.getTitle().equals(findNotice.getTitle()) ? true : false;
        }
        if (notice.getContents() != null) {
            flag = notice.getContents().equals(findNotice.getContents()) ? true : false;
        }
        if (notice.getImgPath() != null) {
            flag = notice.getImgPath().equals(findNotice.getImgPath());
        }
        if (notice.getImgName() != null) {
            flag = notice.getImgName().equals(findNotice.getImgName());
        }
        if (notice.getMainYn() != null) {
            flag = notice.getMainYn().equals(findNotice.getMainYn());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 공지사항 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteNotice(@RequestBody List<Integer> ids) {
        log.info("공지사항 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Notice origin = noticeService.findOne(idL);
            // 이미지 삭제
            File originFile = new File(newfile + "/" + origin.getImgPath());
            if (originFile.exists()) {
                originFile.delete();
            }
            noticeService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

}
