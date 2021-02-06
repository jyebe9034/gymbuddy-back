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

import static com.gymbuddy.backgymbuddy.admin.base.Constants.MEMBER_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController extends BaseController {

    private final String URI_PREFIX = MEMBER_PREFIX;
    private String memberPath = "/resources/static/img/member";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + memberPath;
    private File saveFile = new File(rootPath);

    private final MemberService memberService;

    /**
     * 멤버소개 조회
     */
    @GetMapping(URI_PREFIX + "/all")
    public ResponseEntity<List<Member>> selectMemberList() {
        return createResponseEntity(true, memberService.findAll());
    }

    /**
     * 웹 멤버소개 등록
     */
    @PostMapping(URI_PREFIX + "/newWeb")
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
    @PostMapping(URI_PREFIX + "/newMobile")
    public ResponseEntity<Map<String, Object>> insertMobileMember(@ModelAttribute MemberDto member) {
        log.info("모바일 멤버소개 등록: {}", member);

        memberFileUpload(member);

        Map<String, Object> result = new HashMap<>();
        result.put("id", memberService.save(member));
        return createResponseEntity(true, result);
    }

    private void memberFileUpload(MemberDto member) {
        String imgName = member.getFile().getOriginalFilename();
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
            member.getFile().transferTo(realFile);
            member.setImgName(imgName);
            member.setImgPath(memberPath + realFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 멤버소개 수정
     */
    @PutMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMember(
            @PathVariable("id") Long id, @ModelAttribute MemberDto dto) {
        log.info("멤버소개 수정 id: {}, dto: {}", id, dto);

        Member member = memberService.findOne(id);
        String imgName = dto.getFile().getOriginalFilename();
        if (!member.getImgName().equals(imgName)) {
            try {
                // 이미지 업로드
                File realFile = new File(saveFile + "/" + System.currentTimeMillis() + "_" + imgName);
                dto.getFile().transferTo(realFile);
                dto.setImgName(imgName);
                dto.setImgPath(memberPath + realFile.getName());

                // 기존 이미지를 파일 서버에서 삭제
                File originFile = new File(saveFile + "/" + member.getImgPath());
                if (originFile.exists()) {
                    originFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        memberService.update(id, dto);
        Member findBi = memberService.findOne(id);

        boolean flag = true;
        if (dto.getImgPath() != null) {
            flag = dto.getImgPath().equals(findBi.getImgPath());
        }
        if (dto.getImgName() != null) {
            flag = dto.getImgName().equals(findBi.getImgName());
        }
        if (dto.getWebMobile() != null) {
            flag = dto.getWebMobile().equals(findBi.getWebOrMobile());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
