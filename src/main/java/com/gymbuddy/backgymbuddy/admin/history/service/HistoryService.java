package com.gymbuddy.backgymbuddy.admin.history.service;

import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> findALl() {
        return historyRepository.findAll();
    }

    public List<History> findAllByDate() {
        return historyRepository.findAllByDate();
    }

    public History findOne(Long id) {
        return historyRepository.findById(id).get();
    }

    @Transactional
    public Long save(History history) {
        historyRepository.save(history);
        return history.getId();
    }

    @Transactional
    public void update(Long id, Map<String, Object> param) {
        History history = historyRepository.findById(id).get();
        if (param.get("historyDate") != null) {
            history.setHistoryDate(LocalDateTime.parse(Objects.toString(param.get("historyDate"))));
        }
        if (param.get("title") != null) {
            history.setTitle(Objects.toString(param.get("title")));
        }
    }

    @Transactional
    public Long delete(Long id) {
        historyRepository.deleteById(id);
        return id;
    }
}
