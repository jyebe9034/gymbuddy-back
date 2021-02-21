package com.gymbuddy.backgymbuddy.admin.businessIdentity.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BiDto;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.repository.BiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public Long save(BiDto dto) {
        BusinessIdentity bi = new BusinessIdentity();
        bi.setImgPath(dto.getImgPath());
        bi.setImgName(dto.getImgName());
        bi.setWebMobile(dto.getWebMobile());
        bi.setCreateDate(LocalDateTime.now());
        bi.setUpdateDate(LocalDateTime.now());

        biRepository.save(bi);
        return bi.getId();
    }

    @Transactional
    public void update(Long id, BiDto dto) {
        BusinessIdentity bi = findOne(id);
        if (dto.getImgPath() != null) {
            bi.setImgPath(dto.getImgPath());
        }
        if (dto.getImgName() != null) {
            bi.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            bi.setWebMobile(dto.getWebMobile());
        }
        bi.setUpdateDate(LocalDateTime.now());
    }
}
