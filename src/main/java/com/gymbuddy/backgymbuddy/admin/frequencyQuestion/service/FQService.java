package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service;

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
    public Long save(FrequencyQuestion faq) {
        fqRepository.save(faq);
        return faq.getId();
    }

    @Transactional
    public void update(Long id, Map<String, Object> param) {
        FrequencyQuestion frequencyQuestion = fqRepository.findById(id).get();
        if (param.get("title") != null) {
            frequencyQuestion.setTitle(Objects.toString(param.get("title")));
        }
        if (param.get("contents") != null) {
            frequencyQuestion.setContents(Objects.toString(param.get("contents")));
        }
    }

    @Transactional
    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            fqRepository.deleteById(idL);
        }
    }
}
