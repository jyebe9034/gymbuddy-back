package com.gymbuddy.backgymbuddy.admin.businessIdentity.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.repository.BiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BiService {

    private final BiRepository biRepository;

    public List<BusinessIdentity> findAll() {
        return biRepository.findAll();
    }

    public BusinessIdentity findOne(Long id) {
        return biRepository.findById(id).get();
    }

    @Transactional
    public Long save(BusinessIdentity businessIdentity) {
        biRepository.save(businessIdentity);
        return businessIdentity.getId();
    }

    @Transactional
    public void update(Long id) {
        BusinessIdentity businessIdentity = biRepository.findById(id).get();
        // TODO 이미지 로직
    }

    @Transactional
    public void delete(Long id) {
        biRepository.deleteById(id);
    }

}
