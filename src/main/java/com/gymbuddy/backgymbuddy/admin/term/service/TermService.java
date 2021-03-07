package com.gymbuddy.backgymbuddy.admin.term.service;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.domain.TermDto;
import com.gymbuddy.backgymbuddy.admin.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;

    public List<Term> findAll() {
        return termRepository.findAll();
    }

    public Term findOne(Long id) {
        Optional<Term> byId = termRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 약관입니다.");
        }
        return byId.get();
    }

    public Map<String, Object> findByTitle(String title) {
        Term web = termRepository.findByTitleAndWebMobile(title, WebMobileStatus.WEB);
        Term mobile = termRepository.findByTitleAndWebMobile(title, WebMobileStatus.MOBILE);

        Map<String, Object> result = new HashMap<>();
        result.put("web", web);
        result.put("mobile", mobile);
        return result;
    }

    public List<Term> findPrivatePolicy() {
        return termRepository.findByTitle("개인정보 처리방침");
    }

    public List<Term> findTermsOfUse() {
        return termRepository.findByTitle("이용약관");
    }

    @Transactional
    public Long save(TermDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Term term = new Term();
        if (dto.getTitle() != null) {
            term.setTitle(dto.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (dto.getImgName() != null) {
            term.setImgName(dto.getImgName());
        } else {
            throw new DMException("파일을 입력해주세요.");
        }
        if (dto.getImgPath() != null) {
            term.setImgPath(dto.getImgPath());
        }
        if (dto.getWebMobile() != null) {
            term.setWebMobile(dto.getWebMobile());
        } else {
            throw new DMException("웹모바일 여부를 입력해주세요.");
        }
        term.setCreateId(loginId);
        term.setUpdateId(loginId);

        termRepository.save(term);
        return term.getId();
    }

    @Transactional
    public void update(Long id, TermDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Term term = findOne(id);
        if (dto.getTitle() != null) {
            term.setTitle(dto.getTitle());
        }
        if (dto.getImgPath() != null) {
            term.setImgPath(dto.getImgPath());
        }
        if (dto.getImgName() != null) {
            term.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            term.setWebMobile(dto.getWebMobile());
        }
        term.setUpdateId(loginId);
    }
}
