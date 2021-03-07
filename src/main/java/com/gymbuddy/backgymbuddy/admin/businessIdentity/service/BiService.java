package com.gymbuddy.backgymbuddy.admin.businessIdentity.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BiDto;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.repository.BiRepository;
import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<BusinessIdentity> byId = biRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 BI 이미지입니다.");
        }
        return byId.get();
    }

    @Transactional
    public Long save(BiDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        BusinessIdentity bi = new BusinessIdentity();
        if (dto.getImgName() != null) {
            bi.setImgName(dto.getImgName());
        } else {
            throw new DMException("파일을 입력해주세요.");
        }
        if (dto.getImgPath() != null) {
            bi.setImgPath(dto.getImgPath());
        }
        if (dto.getWebMobile() != null) {
            bi.setWebMobile(dto.getWebMobile());
        } else {
            throw new DMException("웹모바일 여부를 입력해주세요.");
        }
        bi.setCreateId(loginId);
        bi.setUpdateId(loginId);

        biRepository.save(bi);
        return bi.getId();
    }

    @Transactional
    public void update(Long id, BiDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        BusinessIdentity bi = findOne(id);
        if (dto.getImgPath() != null && !bi.getImgPath().equals(dto.getImgPath())) {
            bi.setImgPath(dto.getImgPath());
        } else {
            throw new DMException("이미지를 입력해주세요.");
        }
        if (dto.getImgName() != null && !bi.getImgName().equals(dto.getImgName())) {
            bi.setImgName(dto.getImgName());
        }
        if (dto.getWebMobile() != null) {
            bi.setWebMobile(dto.getWebMobile());
        }
        bi.setUpdateId(loginId);
    }
}
