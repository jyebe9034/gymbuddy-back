package com.gymbuddy.backgymbuddy.admin.businessIdentity.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.base.DirMake;
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

import static com.gymbuddy.backgymbuddy.admin.base.Constants.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BiController extends BaseController {

    private String biPath = "/resources/images/bi";
    private String rootPath = "/home/www" + biPath;
    private File saveFile = DirMake.testdir(rootPath);

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
                try {
                    saveFile.mkdir();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
            bi.getFile().transferTo(realFile);
            bi.setImgName(realFile.getName());
            bi.setImgPath(biPath + "/" + realFile.getName());
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
                try {
                    saveFile.mkdir();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
            bi.getFile().transferTo(realFile);
            bi.setImgName(realFile.getName());
            bi.setImgPath(biPath + "/" + realFile.getName());
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
    @PutMapping(ADMIN_BI_PREFIX + "/update/{id}")
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
                    dto.setImgName(realFile.getName());
                    dto.setImgPath(biPath + "/" + realFile.getName());

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
