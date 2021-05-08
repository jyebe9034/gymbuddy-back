package com.gymbuddy.backgymbuddy.admin.mission.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.base.DirMake;
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

    private String missionPath = "/resources/images/mission";
    private String rootPath = "/home/www" + missionPath;
    private File saveFile = DirMake.testdir(rootPath);

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
    @PostMapping(ADMIN_MISSION_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertMission(@ModelAttribute MissionDto dto) {
        log.info("미션 등록: {}", dto);

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
                dto.setImgPath1(missionPath + "/" + realFile1.getName());
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
                dto.getFile2().transferTo(realFile2);
                dto.setImgName2(realFile2.getName());
                dto.setImgPath2(missionPath + "/" + realFile2.getName());
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
                dto.setImgPath3(missionPath + "/" + realFile3.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
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
            try {
                File realFile1 = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName1);
                dto.getFile1().transferTo(realFile1);
                dto.setImgName1(realFile1.getName());
                dto.setImgPath1(missionPath + "/" + realFile1.getName());

                if (mission.getImgName1() != null) {
                    File originFile = new File(mission.getImgPath1());
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
                dto.setImgPath2(missionPath + "/" + realFile2.getName());

                if (mission.getImgName2() != null) {
                    File originFile = new File(mission.getImgPath2());
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
                dto.setImgPath3(missionPath + "/" + realFile3.getName());

                if (mission.getImgName3() != null) {
                    File originFile = new File(mission.getImgPath3());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        missionService.update(id, dto);
        Mission findMission = missionService.findOne(id);

        boolean flag = true;
        if (dto.getContents() != null) {
            flag = dto.getContents().equals(findMission.getContents()) ? true : false;
        }
        if (dto.getImgPath1() != null) {
            flag = dto.getImgPath1().equals(findMission.getImgPath1()) ? true : false;
        }
        if (dto.getImgName1() != null) {
            flag = dto.getImgName1().equals(findMission.getImgName1()) ? true : false;
        }
        if (dto.getImgPath2() != null) {
            flag = dto.getImgPath2().equals(findMission.getImgPath2()) ? true : false;
        }
        if (dto.getImgName2() != null) {
            flag = dto.getImgName2().equals(findMission.getImgName2()) ? true : false;
        }
        if (dto.getImgPath3() != null) {
            flag = dto.getImgPath3().equals(findMission.getImgPath3()) ? true : false;
        }
        if (dto.getImgName3() != null) {
            flag = dto.getImgName3().equals(findMission.getImgName3()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
