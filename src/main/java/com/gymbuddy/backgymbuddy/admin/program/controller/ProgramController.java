package com.gymbuddy.backgymbuddy.admin.program.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import com.gymbuddy.backgymbuddy.admin.program.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProgramController extends BaseController {

    private final ProgramService programService;

    /**
     * 전체 프로그램 조회
     */
    @GetMapping("/allProgram")
    public List<Program> selectProgramList() {
        return null;
    }

    /**
     * 프로그램 상세
     */
    @GetMapping("/programDetail/{no}")
    public Map<String, Object> selectProgramDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 프로그램 등록
     */
    @PostMapping("/newProgram")
    public Map<String, Object> insertProgram(@RequestBody Map<String, Object> param) {
        // 여기에서 받은 프로그램 + 프로그램 옵션...
        return null;
    }

    /**
     * 프로그램 수정
     */
    @PostMapping("/updateProgram")
    public Map<String, Object> updateProgram(@RequestBody Map<String, Object> param) {
        // 프로그램 + 프로그램 옵션 수정을 같이 처리해야 함..
        return null;
    }

    /**
     * 프로그램 삭제
     */
    @GetMapping("/deleteProgram")
    public Map<String, Object> deleteProgram(@RequestParam Map<String, Object> param) {
        return null;
    }
}
