package com.gymbuddy.backgymbuddy.admin.member.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import com.gymbuddy.backgymbuddy.admin.member.domain.MemberDto;
import com.gymbuddy.backgymbuddy.admin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 전체 멤버이미지 조회
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * 멤버이미지 한개 조회
     */
    public Member findOne(Long id) {
        Optional<Member> byId = memberRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 멤버 이미지입니다.");
        }
        return byId.get();
    }

    /**
     * 멤버이미지 등록
     */
    @Transactional
    public Long save(MemberDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Member member = new Member();
        if (dto.getImgPath() != null) {
            member.setImgPath(dto.getImgPath());
        } else {
            throw new DMException("파일을 입력해주세요.");
        }
        if (dto.getImgName() != null) {
            member.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            member.setWebMobile(dto.getWebMobile());
        } else {
            throw new DMException("웹모바일 여부를 입력해주세요.");
        }
        member.setCreateId(loginId);
        member.setUpdateId(loginId);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 멤버이미지 수정
     */
    @Transactional
    public void update(Long id, MemberDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Member member = findOne(id);
        if (dto.getImgPath() != null && !member.getImgPath().equals(dto.getImgPath())) {
            member.setImgPath(dto.getImgPath());
        } else {
            throw new DMException("파일을 입력해주세요.");
        }
        if (dto.getImgName() != null && !member.getImgName().equals(dto.getImgName())) {
            member.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            member.setWebMobile(dto.getWebMobile());
        }
        member.setUpdateId(loginId);
    }
}
