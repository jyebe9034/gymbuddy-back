package com.gymbuddy.backgymbuddy.admin.term.service;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.domain.TermDto;
import com.gymbuddy.backgymbuddy.admin.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return termRepository.findById(id).get();
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
        Term term = new Term();
        term.setTitle(dto.getTitle());
        term.setImgName(dto.getImgName());
        term.setImgPath(dto.getImgPath());
        term.setWebMobile(dto.getWebMobile());
        term.setCreateDate(LocalDateTime.now());
        term.setUpdateDate(LocalDateTime.now());

        termRepository.save(term);
        return term.getId();
    }

    @Transactional
    public void update(Long id, TermDto dto) {
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
        term.setUpdateDate(LocalDateTime.now());
    }
}
