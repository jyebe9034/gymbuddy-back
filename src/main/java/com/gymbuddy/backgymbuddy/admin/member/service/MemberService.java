package com.gymbuddy.backgymbuddy.admin.member.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import com.gymbuddy.backgymbuddy.admin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    public Member findByStatus(WebMobileStatus webMobileStatus) {
        return em.find(Member.class, webMobileStatus);
    }

    @Transactional
    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
