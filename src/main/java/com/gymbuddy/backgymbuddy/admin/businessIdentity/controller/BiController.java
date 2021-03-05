package com.gymbuddy.backgymbuddy.admin.businessIdentity.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BiDto;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.service.BiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_HISTORY_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.BI_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BiController extends BaseController {

    private String biPath = "/resources/static/img/bi";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + biPath;
    private File saveFile = new File(rootPath);

    private final BiService biService;

    /**
     * 웹 BI 등록
     */
    @PostMapping(BI_PREFIX + "/newWeb")
    public ResponseEntity<Map<String, Object>> insertWebBi(@ModelAttribute BiDto bi) {
        log.info("웹 BI 등록: {}", bi);

        String imgName = bi.getFile().getOriginalFilename();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
            bi.getFile().transferTo(realFile);
            bi.setImgName(imgName);
            bi.setImgPath(saveFile + "/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", biService.save(bi));
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 BI 등록
     */
    @PostMapping(BI_PREFIX + "/newMobile")
    public ResponseEntity<Map<String, Object>> insertMobileBi(@ModelAttribute BiDto bi) {
        log.info("모바일 BI 등록: {}", bi);

        String imgName = bi.getFile().getOriginalFilename();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
            bi.getFile().transferTo(realFile);
            bi.setImgName(imgName);
            bi.setImgPath(saveFile + "/" + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", biService.save(bi));
        return createResponseEntity(true, result);
    }

    /**
     * BI 수정
     */
    @PutMapping(ADMIN_HISTORY_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateBi(
            @PathVariable("id") Long id, @ModelAttribute BiDto dto) {
        log.info("BI 수정 id: {}, bi: {}", id, dto);

        BusinessIdentity bi = biService.findOne(id);

        if (dto.getFile() != null) {
            String imgName = dto.getFile().getOriginalFilename();
            if (!bi.getImgName().equals(imgName)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
                    dto.getFile().transferTo(realFile);
                    dto.setImgName(imgName);
                    dto.setImgPath(saveFile + "/" + realFile.getName());

                    File originFile = new File(bi.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        biService.update(id, dto);
        BusinessIdentity findBi = biService.findOne(id);

        boolean flag = true;
        if (dto.getImgPath() != null) {
            flag = dto.getImgPath().equals(findBi.getImgPath()) ? true : false;
        }
        if (dto.getImgName() != null) {
            flag = dto.getImgName().equals(findBi.getImgName()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
