package com.gymbuddy.backgymbuddy.admin.program.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramDto;
import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOption;
import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOptionDto;
import com.gymbuddy.backgymbuddy.admin.program.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.PROGRAM_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProgramController extends BaseController {

    private String programPath = "/resources/static/img/program";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + programPath;
    private File newfile = new File(rootPath);

    private final ProgramService programService;

    /**
     * 전체 프로그램 조회(관리자)
     */
    @GetMapping(PROGRAM_PREFIX + "/all/{page}")
    public ResponseEntity<List<Program>> selectProgramList(@PathVariable("page") int page) {
        return createResponseEntity(true, programService.findAll(page));
    }

    /**
     * 프로그램 상세
     */
    @GetMapping(PROGRAM_PREFIX + "/detail/{id}")
    public ResponseEntity<Program> selectProgramDetail(@PathVariable("id") Long id) {
        log.info("프로그램 아이디로 조회: {}", id);
        return createResponseEntity(true, programService.findOne(id));
    }

    /**
     * 프로그램 등록
     */
    @PostMapping(PROGRAM_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertProgram(@ModelAttribute ProgramDto program) {
        // 여기에서 받은 프로그램 + 프로그램 옵션...
        log.info("프로그램 등록: {}", program);

        // 썸네일 이미지 업로드
        if (program.getThumbnailFile() != null) {
            String thumbnailName = program.getThumbnailFile().getOriginalFilename();
            try {
                if (!newfile.exists()) {
                    newfile.mkdir();
                }
                File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + thumbnailName);
                program.getThumbnailFile().transferTo(realFile);
                program.setThumbnailImgName(realFile.getName());
                program.setThumbnailImgPath(programPath + "/" + realFile.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        // 상세 이미지 업로드
        if (program.getDetailFile() != null) {
            String detailName = program.getDetailFile().getOriginalFilename();
            try {
                if (!newfile.exists()) {
                    newfile.mkdir();
                }
                File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + detailName);
                program.getDetailFile().transferTo(realFile);
                program.setDetailImgName(realFile.getName());
                program.setDetailImgPath(programPath + "/" + realFile.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", programService.save(program));
        return createResponseEntity(true, result);
    }

    /**
     * 프로그램 수정
     */
    @PutMapping(PROGRAM_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateProgram(@PathVariable("id") Long id, @RequestBody ProgramDto program) {
        // 프로그램 + 프로그램 옵션 수정을 같이 처리해야 함..
        log.info("컬럼 수정 - id: {}, program: {}", id, program);

        Program origin = programService.findOne(id);
        // 썸네일 이미지 업로드
        if (program.getThumbnailFile() != null) {
            String thumbnailName = program.getThumbnailFile().getOriginalFilename();
            try {
                File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + thumbnailName);
                program.getThumbnailFile().transferTo(realFile);
                program.setThumbnailImgName(realFile.getName());
                program.setThumbnailImgPath(programPath + "/" + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                File originThumbFile = new File(newfile + "/" + origin.getThumbnailImgPath());
                if (originThumbFile.exists()) {
                    originThumbFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        // 상세 이미지 업로드
        if (program.getDetailFile() != null) {
            String detailName = program.getDetailFile().getOriginalFilename();
            try {
                File realFile = new File(newfile + "/" + System.currentTimeMillis() + "_" + detailName);
                program.getDetailFile().transferTo(realFile);
                program.setDetailImgName(realFile.getName());
                program.setDetailImgPath(programPath + "/" + realFile.getName());

                // 기존 이미지 파일 서버에서 삭제
                File originDetailFile = new File(newfile + "/" + origin.getDetailImgPath());
                if (originDetailFile.exists()) {
                    originDetailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        programService.update(id, program);
        Program findProgram = programService.findOne(id);

        boolean flag = true;
        if (program.getTitle() != null) {
            flag = program.getTitle().equals(findProgram.getTitle()) ? true : false;
        }
        if (program.getCoach() != null) {
            flag = program.getCoach().equals(findProgram.getCoach()) ? true : false;
        }
        if (program.getClassAddress() != null) {
            flag = program.getClassAddress().equals(findProgram.getClassAddress()) ? true : false;
        }
        if (program.getClassDate() != null) {
            flag = program.getClassDate().equals(findProgram.getClassDate()) ? true : false;
        }
        if (program.getClassTime() != null) {
            flag = program.getClassTime().equals(findProgram.getClassTime()) ? true : false;
        }
        if (program.getPrice() != null) {
            flag = program.getPrice().compareTo(findProgram.getPrice()) == 0 ? true : false;
        }
        if (program.getThumbnailImgPath() != null) {
            flag = program.getThumbnailImgPath().equals(findProgram.getThumbnailImgPath()) ? true : false;
        }
        if (program.getThumbnailImgName() != null) {
            flag = program.getThumbnailImgName().equals(findProgram.getThumbnailImgName()) ? true : false;
        }
        if (program.getDetailImgPath() != null) {
            flag = program.getDetailImgPath().equals(findProgram.getDetailImgPath()) ? true : false;
        }
        if (program.getDetailImgName() != null) {
            flag = program.getDetailImgName().equals(findProgram.getDetailImgName()) ? true : false;
        }

        List<ProgramOptionDto> optionList = program.getOptionList();
        for (ProgramOptionDto dto : optionList) {
            ProgramOption findOption = programService.findOneOption(dto.getId());
            if (dto.getClassDateTime() != null) {
                flag = dto.getClassDateTime().equals(findOption.getClassDateTime()) ? true : false;
            }
            if (dto.getUserCount() != 0) {
                flag = dto.getUserCount() == findOption.getUserCount() ? true : false;
            }
            if (dto.getAddPrice() != null) {
                flag = dto.getAddPrice().compareTo(findOption.getAddPrice()) == 0 ? true : false;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 프로그램 삭제
     */
    @DeleteMapping(PROGRAM_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteProgram(@RequestBody List<Integer> ids) {
        log.info("프로그램 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Program origin = programService.findOne(idL);
            // 썸네일 이미지 삭제
            File originThumbFile = new File(newfile + "/" + origin.getThumbnailImgPath());
            if (originThumbFile.exists()) {
                originThumbFile.delete();
            }
            // 상세 이미지 삭제
            File originDetailFile = new File(newfile + "/" + origin.getDetailImgPath());
            if (originDetailFile.exists()) {
                originDetailFile.delete();
            }
            programService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}
