package com.gymbuddy.backgymbuddy.admin.mission.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.mission.domain.Mission;
import com.gymbuddy.backgymbuddy.admin.mission.domain.MissionDto;
import com.gymbuddy.backgymbuddy.admin.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_MISSION_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.MISSION_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MissionController extends BaseController {

    private String missionPath = "/resources/static/img/mission";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + missionPath;
    private File saveFile = new File(rootPath);

    private final MissionService missionService;

    /**
     * 미션과 비전 조회
     */
    @GetMapping(MISSION_PREFIX + "/allByMap")
    public ResponseEntity<Map<String, Object>> selectMission() {
        return createResponseEntity(true, missionService.findAllByMap());
    }

    /**
     * 미션 등록
     */
    @PostMapping(MISSION_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMission(@ModelAttribute MissionDto dto) {
        log.info("공지사항 등록: {}", dto);

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
            dto.setImgPath1(missionPath + "/" + realFile1.getName());

            // 파일2
            File realFile2 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName2);
            dto.getFile2().transferTo(realFile2);
            dto.setImgName2(imgName2);
            dto.setImgPath2(missionPath + "/" + realFile2.getName());

            // 파일3
            File realFile3 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName3);
            dto.getFile3().transferTo(realFile3);
            dto.setImgName3(imgName3);
            dto.setImgPath3(missionPath + "/" + realFile3.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", missionService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 미션 수정
     */
    @PutMapping(ADMIN_MISSION_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMission(
            @PathVariable("id") Long id, @ModelAttribute MissionDto dto) {
        log.info("미션 수정 id: {}, dto: {}", id, dto);

        Mission mission = missionService.findOne(id);

        if (dto.getFile1() != null) {
            String imgName1 = dto.getFile1().getOriginalFilename();
            if (!mission.getImgName1().equals(imgName1)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName1);
                    dto.getFile1().transferTo(realFile);
                    dto.setImgName1(imgName1);
                    dto.setImgPath1(missionPath + "/" + realFile.getName());

                    File originFile = new File(saveFile + "/" + dto.getImgPath1());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        if (dto.getFile2() != null) {
            String imgName2 = dto.getFile2().getOriginalFilename();
            if (!mission.getImgName2().equals(imgName2)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName2);
                    dto.getFile2().transferTo(realFile);
                    dto.setImgName2(imgName2);
                    dto.setImgPath2(missionPath + "/" + realFile.getName());

                    File originFile = new File(saveFile + "/" + dto.getImgPath2());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        if (dto.getFile3() != null) {
            String imgName3 = dto.getFile3().getOriginalFilename();
            if (!mission.getImgName3().equals(imgName3)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName3);
                    dto.getFile3().transferTo(realFile);
                    dto.setImgName3(imgName3);
                    dto.setImgPath3(missionPath + "/" + realFile.getName());

                    File originFile = new File(saveFile + "/" + dto.getImgPath3());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        missionService.update(id, dto);
        Mission findMission = missionService.findOne(id);

        boolean flag = true;
        if (dto.getContents() != null) {
            flag = dto.getContents().equals(findMission.getContents()) ? true : false;
        }
        if (dto.getImgPath1() != null) {
            flag = dto.getImgPath1().equals(findMission.getImgPath1());
        }
        if (dto.getImgName1() != null) {
            flag = dto.getImgName1().equals(findMission.getImgName1());
        }
        if (dto.getImgPath2() != null) {
            flag = dto.getImgPath2().equals(findMission.getImgPath2());
        }
        if (dto.getImgName2() != null) {
            flag = dto.getImgName2().equals(findMission.getImgName2());
        }
        if (dto.getImgPath3() != null) {
            flag = dto.getImgPath3().equals(findMission.getImgPath3());
        }
        if (dto.getImgName3() != null) {
            flag = dto.getImgName3().equals(findMission.getImgName3());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
