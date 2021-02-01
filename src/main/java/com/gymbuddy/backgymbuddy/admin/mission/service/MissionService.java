package com.gymbuddy.backgymbuddy.admin.mission.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.repository.BiRepository;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.repository.HistoryRepository;
import com.gymbuddy.backgymbuddy.admin.history.service.HistoryService;
import com.gymbuddy.backgymbuddy.admin.mission.domain.Mission;
import com.gymbuddy.backgymbuddy.admin.mission.repository.MissionRepository;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final HistoryRepository historyRepository;
    private final BiRepository biRepository;

    public Mission find(Long id) {
        return missionRepository.findById(id).get();
    }

    public void findAllToAdmin(Long id) {
        List<History> histories = new ArrayList<>();
        histories = historyRepository.findAll();
        List<BusinessIdentity> biList = new ArrayList<>();
        biList = biRepository.findAll();
        missionRepository.findById(id).get();
    }

    @Transactional
    public Long save(Mission mission) {
        missionRepository.save(mission);
        return mission.getId();
    }

    @Transactional
    public void update(Long id, Map<String, Object> param) {
        Mission mission = missionRepository.findById(id).get();
        // TODO 이미지 로직 추가
        if (param.get("contents") != null) {
            mission.setContents(Objects.toString(param.get("contents")));
        }
    }
}
