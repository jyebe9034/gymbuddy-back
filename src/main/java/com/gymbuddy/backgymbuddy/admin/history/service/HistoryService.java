package com.gymbuddy.backgymbuddy.admin.history.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> findALl() {
        return historyRepository.findAll();
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
    public void update(Long id) {
        History history = historyRepository.findById(id).get();
    }

    @Transactional
    public Long delete(Long id) {
        historyRepository.deleteById(id);
        return id;
    }
}
