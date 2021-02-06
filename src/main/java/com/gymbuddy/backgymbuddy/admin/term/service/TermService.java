package com.gymbuddy.backgymbuddy.admin.term.service;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.domain.TermDto;
import com.gymbuddy.backgymbuddy.admin.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermService {

    private final EntityManager em;
    private final TermRepository termRepository;

    public List<Term> findAll() {
        return termRepository.findAll();
    }

    public Term findOne(Long id) {
        return termRepository.findById(id).get();
    }

    public List<Term> findByTitle(String title) {
        /*List<Term> web = termRepository.findByTitleAndStatus(title, WebMobileStatus.WEB);
        List<Term> mobile = termRepository.findByTitleAndStatus(title, WebMobileStatus.MOBILE);

        Map<String, Object> result = new HashMap<>();
        result.put("web", web);
        result.put("mobile", mobile);
        return result;*/
        return termRepository.findByTitle(title);
    }

    public List<Term> findPrivatePolicy() {
        return termRepository.findByTitle("개인정보 처리 방침");
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
    }
}
