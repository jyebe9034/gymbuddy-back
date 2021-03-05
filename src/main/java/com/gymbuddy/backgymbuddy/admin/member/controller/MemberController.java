package com.gymbuddy.backgymbuddy.admin.member.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import com.gymbuddy.backgymbuddy.admin.member.domain.MemberDto;
import com.gymbuddy.backgymbuddy.admin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_MEMBER_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.MEMBER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController extends BaseController {

    private String memberPath = "/resources/static/img/member";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + memberPath;
    private File saveFile = new File(rootPath);

    private final MemberService memberService;

    /**
     * 멤버소개 조회
     */
    @GetMapping(MEMBER_PREFIX + "/all")
    public ResponseEntity<List<Member>> selectMemberList() {
        return createResponseEntity(true, memberService.findAll());
    }

    /**
     * 웹 멤버소개 등록
     */
    @PostMapping(MEMBER_PREFIX + "/newWeb")
    public ResponseEntity<Map<String, Object>> insertWebMember(@ModelAttribute MemberDto member) {
        log.info("웹 멤버소개 등록: {}", member);

        memberFileUpload(member);

        Map<String, Object> result = new HashMap<>();
        result.put("id", memberService.save(member));
        return createResponseEntity(true, result);
    }

    /**
     * 모바일 멤버소개 등록
     */
    @PostMapping(MEMBER_PREFIX + "/newMobile")
    public ResponseEntity<Map<String, Object>> insertMobileMember(@ModelAttribute MemberDto member) {
        log.info("모바일 멤버소개 등록: {}", member);

        memberFileUpload(member);

        Map<String, Object> result = new HashMap<>();
        result.put("id", memberService.save(member));
        return createResponseEntity(true, result);
    }

    private void memberFileUpload(MemberDto dto) {
        String imgName = dto.getFile().getOriginalFilename();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            if (!dto.getFile().isEmpty()) {
                File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
                dto.getFile().transferTo(realFile);
                dto.setImgName(imgName);
                dto.setImgPath(saveFile + "/" + realFile.getName());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 멤버소개 수정
     */
    @PutMapping(ADMIN_MEMBER_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMember(
            @PathVariable("id") Long id, @ModelAttribute MemberDto dto) {
        log.info("멤버소개 수정 id: {}, dto: {}", id, dto);

        Member member = memberService.findOne(id);

        if (dto.getFile() != null) {
            String imgName = dto.getFile().getOriginalFilename();
            if (!member.getImgName().equals(imgName)) {
                try {
                    File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
                    dto.getFile().transferTo(realFile);
                    dto.setImgName(imgName);
                    dto.setImgPath(saveFile + "/" + realFile.getName());

                    File originFile = new File(member.getImgPath());
                    if (originFile.exists()) {
                        originFile.delete();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

        memberService.update(id, dto);
        Member findMember = memberService.findOne(id);

        boolean flag = true;
        if (dto.getImgPath() != null) {
            flag = dto.getImgPath().equals(findMember.getImgPath()) ? true : false;
        }
        if (dto.getImgName() != null) {
            flag = dto.getImgName().equals(findMember.getImgName()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
