package com.gymbuddy.backgymbuddy.admin.history.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.domain.HistoryDto;
import com.gymbuddy.backgymbuddy.admin.history.repository.HistoryRepository;
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
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> findALl() {
        return historyRepository.findAll();
    }

    public History findOne(Long id) {
        Optional<History> byId = historyRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 활동기록입니다.");
        }
        return byId.get();
    }

    @Transactional
    public Long save(HistoryDto dto) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        History history = new History();
        if (dto.getHistoryDate() != null) {
            history.setHistoryDate(dto.getHistoryDate());
        } else {
            throw new DMException("날짜를 입력해주세요.");
        }
        if (dto.getTitle() != null) {
            history.setTitle(dto.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        history.setCreateId(loginId);
        history.setUpdateId(loginId);

        historyRepository.save(history);
        return history.getId();
    }

    @Transactional
    public void update(List<HistoryDto> dtoList) {
        historyRepository.deleteAll();
        for (HistoryDto dto : dtoList) {
            save(dto);
        }
    }
}
