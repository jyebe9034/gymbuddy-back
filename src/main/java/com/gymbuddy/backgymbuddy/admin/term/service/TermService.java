package com.gymbuddy.backgymbuddy.admin.term.service;

import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import com.gymbuddy.backgymbuddy.admin.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;

    public List<Term> findALl() {
        return termRepository.findAll();
    }

    public Term findOne(Long id) {
        return termRepository.findById(id).get();
    }

    @Transactional
    public Long save(Term term) {
        termRepository.save(term);
        return term.getId();
    }

    @Transactional
    public void update(Long id, String title) {
        Term term = termRepository.findById(id).get();
        term.setTitle(title);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedRows = termRepository.deleteByIds(ids);
        if (ids.size() == deletedRows.intValue()) {
            return 1;
        }
        return 0;
    }
}
