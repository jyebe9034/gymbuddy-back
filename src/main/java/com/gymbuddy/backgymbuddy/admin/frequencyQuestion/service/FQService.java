package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service;

import com.gymbuddy.backgymbuddy.admin.enums.category.FaqEnum;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FQDto;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain.FrequencyQuestion;
import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository.FQRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> findAllByMap() {
        List<FrequencyQuestion> account = fqRepository.findFrequencyQuestionByCategoryId(FaqEnum.AC);
        List<FrequencyQuestion> payment = fqRepository.findFrequencyQuestionByCategoryId(FaqEnum.PY);
        List<FrequencyQuestion> shipment = fqRepository.findFrequencyQuestionByCategoryId(FaqEnum.SH);
        List<FrequencyQuestion> programAndGoods = fqRepository.findFrequencyQuestionByCategoryId(FaqEnum.PD);
        List<FrequencyQuestion> homepage = fqRepository.findFrequencyQuestionByCategoryId(FaqEnum.HP);
        List<FrequencyQuestion> etc = fqRepository.findFrequencyQuestionByCategoryId(FaqEnum.ETC);

        Map<String, Object> result = new HashMap<>();
        result.put("programAndGoods", programAndGoods);
        result.put("account", account);
        result.put("payment", payment);
        result.put("shipment", shipment);
        result.put("homepage", homepage);
        result.put("etc", etc);
        return result;
    }

    public FrequencyQuestion findOne(Long id) {
        return fqRepository.findById(id).get();
    }

    @Transactional
    public Long save(FQDto dto) {
        FrequencyQuestion frequencyQuestion = new FrequencyQuestion();
        if (dto.getTitle() != null) {
            frequencyQuestion.setTitle(dto.getTitle());
        }
        if (dto.getContents() != null) {
            frequencyQuestion.setContents(dto.getContents());
        }
        if (dto.getCategoryId() != null) {
            frequencyQuestion.setCategoryId(dto.getCategoryId());
        }

        fqRepository.save(frequencyQuestion);
        return frequencyQuestion.getId();
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
