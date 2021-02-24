package com.gymbuddy.backgymbuddy.admin.member.service;

import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import com.gymbuddy.backgymbuddy.admin.member.domain.MemberDto;
import com.gymbuddy.backgymbuddy.admin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public Long save(MemberDto dto) {
        Member member = new Member();
        if (dto.getImgPath() != null) {
            member.setImgPath(dto.getImgPath());
        }
        if (dto.getImgName() != null) {
            member.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            member.setWebMobile(dto.getWebMobile());
        }

        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void update(Long id, MemberDto dto) {
        Member member = findOne(id);
        if (dto.getImgPath() != null) {
            member.setImgPath(dto.getImgPath());
        }
        if (dto.getImgName() != null) {
            member.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            member.setWebMobile(dto.getWebMobile());
        }
    }
}
