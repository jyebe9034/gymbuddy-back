package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FQDto;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository.FQRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class FQService {

    private final FQRepository fqRepository;

    public List<FrequencyQuestion> findAll(int page) {
        return fqRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    public int selectTotalCount() {
        return fqRepository.findAll().size();
    }

    public List<FrequencyQuestion> findAllByCategoryId() {
        return fqRepository.findAllByCategoryId();
    }

    public FrequencyQuestion findOne(Long id) {
        Optional<FrequencyQuestion> byId = fqRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 자주 묻는 질문입니다.");
        }
        return byId.get();
    }

    @Transactional
    public Long save(FQDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        FrequencyQuestion frequencyQuestion = new FrequencyQuestion();
        if (dto.getTitle() != null) {
            frequencyQuestion.setTitle(dto.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (dto.getContents() != null) {
            frequencyQuestion.setContents(dto.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        if (dto.getCategoryId() != null) {
            frequencyQuestion.setCategoryId(dto.getCategoryId());
        } else {
            throw new DMException("카테고리를 입력해주세요.");
        }
        frequencyQuestion.setCreateId(loginId);
        frequencyQuestion.setUpdateId(loginId);

        fqRepository.save(frequencyQuestion);
        return frequencyQuestion.getId();
    }

    @Transactional
    public void update(Long id, FQDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        FrequencyQuestion fq = findOne(id);
        if (dto.getTitle() != null) {
            fq.setTitle(dto.getTitle());
        }
        if (dto.getContents() != null) {
            fq.setContents(dto.getContents());
        }
        if (dto.getCategoryId() != null) {
            fq.setCategoryId(dto.getCategoryId());
        }
        fq.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        fqRepository.deleteById(id);
    }
}
