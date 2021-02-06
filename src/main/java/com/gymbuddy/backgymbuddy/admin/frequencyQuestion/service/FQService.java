package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service;

import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FQDto;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository.FQRepository;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FQService {

    private final FQRepository fqRepository;

    public List<FrequencyQuestion> findAll(int page) {
        return fqRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public FrequencyQuestion findOne(Long id) {
        return fqRepository.findById(id).get();
    }

    @Transactional
    public Long save(FQDto dto) {
        FrequencyQuestion fq = new FrequencyQuestion();
        fq.setCategoryId(dto.getCategoryId());
        fq.setTitle(dto.getTitle());
        fq.setContents(dto.getContents());

        fqRepository.save(fq);
        return fq.getId();
    }

    @Transactional
    public void update(Long id, FQDto dto) {
        FrequencyQuestion fq = findOne(id);
        if (dto.getCategoryId() != null) {
            fq.setCategoryId(dto.getCategoryId());
        }
        if (dto.getTitle() != null) {
            fq.setTitle(dto.getTitle());
        }
        if (dto.getContents() != null) {
            fq.setContents(dto.getContents());
        }
    }

    @Transactional
    public void delete(Long id) {
        fqRepository.deleteById(id);
    }
}
