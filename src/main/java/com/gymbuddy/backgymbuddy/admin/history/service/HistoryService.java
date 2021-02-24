package com.gymbuddy.backgymbuddy.admin.history.service;

import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.domain.HistoryDto;
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
    public Long save(HistoryDto dto) {
        History history = new History();
        if (dto.getHistoryDate() != null) {
            history.setHistoryDate(dto.getHistoryDate());
        }
        if (dto.getTitle() != null) {
            history.setTitle(dto.getTitle());
        }

        historyRepository.save(history);
        return history.getId();
    }

    @Transactional
    public void update(Long id, HistoryDto dto) {
        History history = findOne(id);
        if (dto.getHistoryDate() != null) {
            history.setHistoryDate(dto.getHistoryDate());
        }
        if (dto.getTitle() != null) {
            history.setTitle(dto.getTitle());
        }
    }

    @Transactional
    public void delete(Long id) {
        historyRepository.deleteById(id);
    }
}
