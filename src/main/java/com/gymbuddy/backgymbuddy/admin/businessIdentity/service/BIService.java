package com.gymbuddy.backgymbuddy.admin.businessIdentity.service;

import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.repository.BIRepository;
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
public class BIService {

    private final BIRepository biRepository;

    public List<BusinessIdentity> findALl() {
        return biRepository.findAll();
    }

    public BusinessIdentity findOne(Long id) {
        return biRepository.findById(id).get();
    }

    public BusinessIdentity findWeb(WebMobile webMobile) {
        return biRepository.findWeb(WebMobile.WEB);
    }

    public BusinessIdentity findMobile(WebMobile webMobile) {
        return biRepository.findMobile(WebMobile.MOBILE);
    }

    @Transactional
    public Long save(BusinessIdentity bi) {
        biRepository.save(bi);
        return bi.getId();
    }

    @Transactional
    public void update(Long id) {
        BusinessIdentity bi = biRepository.findById(id).get();
    }

    @Transactional
    public Long delete(Long id) {
        biRepository.deleteById(id);
        return id;
    }
}
