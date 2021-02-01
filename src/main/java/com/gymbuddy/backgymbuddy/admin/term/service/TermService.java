package com.gymbuddy.backgymbuddy.admin.term.service;

import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public Term findByTitle(String title) {
        return termRepository.findByTitle(title);
    }

    public Term findPrivatePolicy(String title) {
        Term privatePolicy = termRepository.findByTitle(title);
        return privatePolicy;
    }

    public Term findTermsOfUse(String title) {
        Term termsOfUse = termRepository.findByTitle(title);
        return termsOfUse;
    }

    @Transactional
    public Long save(Term term) {
        termRepository.save(term);
        return term.getId();
    }

    public void update(Long id, Map<String, Object> param) {
        Term term = termRepository.findById(id).get();
        // TODO 이미지 로직 추가
        if (param.get("title") != null) {
            term.setTitle(Objects.toString(param.get("title")));
        }
    }
}
