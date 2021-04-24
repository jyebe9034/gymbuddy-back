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

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_NOTICE_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.NOTICE_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController extends BaseController {

    private String noticePath = "/resources/images/notice";
    private String rootPath = "/home/www" + noticePath;
    private File newFile = new File(rootPath);

    private final NoticeService noticeService;

    /**
     * 전체 공지사항 갯수 조회
     */
    @GetMapping(NOTICE_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectNoticeTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", noticeService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 전체 공지사항 조회(관리자)
     */
    @GetMapping(NOTICE_PREFIX + "/all/{page}")
    public ResponseEntity<List<Notice>> selectNoticeList(@PathVariable("page") int page) {
        return createResponseEntity(true, noticeService.findAll(page));
    }

    /**
     * 공지사항 상세
     */
    @GetMapping(NOTICE_PREFIX + "/detail/{id}")
    public ResponseEntity<Notice> selectNoticeDetail(@PathVariable("id") Long id) {
        log.info("공지사항 아이디로 조회: {}", id);
        return createResponseEntity(true, noticeService.findOne(id));
    }

    /**
     * 공지사항 등록
     */
    @PostMapping(ADMIN_NOTICE_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertNotice(@ModelAttribute NoticeDto notice) {
        log.info("공지사항 등록: {}", notice);

        // 이미지가 있는 경우에만 실행
        if (notice.getFile() != null) {
            String filename = notice.getFile().getOriginalFilename();
            try {
                if (!newFile.exists()) {
                    try {
                        newFile.mkdir();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                notice.getFile().transferTo(realFile);
                notice.setImgName(realFile.getName());
                notice.setImgPath(noticePath + "/" + realFile.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", noticeService.save(notice));
        return createResponseEntity(true, result);
    }

    /**
     * 공지사항 수정
     */
    @PutMapping(ADMIN_NOTICE_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateNotice(@PathVariable("id") Long id, @ModelAttribute NoticeDto notice) {
        log.info("공지사항 수정 - id: {}, notice: {}", id, notice);

        // 이미지가 있는 경우에만 실행
        if (notice.getFile() != null) {
            String filename = notice.getFile().getOriginalFilename();
            // 이미지 업로드
            try {
                File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + filename);
                notice.getFile().transferTo(realFile);
                notice.setImgName(realFile.getName());
                notice.setImgPath(noticePath + "/" + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                Notice origin = noticeService.findOne(id);
                if (origin.getImgPath() != null) {
                    File originFile = new File(origin.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
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
            flag = notice.getImgPath().equals(findNotice.getImgPath()) ? true : false;
        }
        if (notice.getImgName() != null) {
            flag = notice.getImgName().equals(findNotice.getImgName()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 공지사항 삭제
     */
    @DeleteMapping(ADMIN_NOTICE_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteNotice(@RequestBody List<Integer> ids) {
        log.info("공지사항 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Notice origin = noticeService.findOne(idL);
            // 이미지 삭제
            if (origin.getImgPath() != null) {
                File originFile = new File(origin.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            }
            noticeService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

}
