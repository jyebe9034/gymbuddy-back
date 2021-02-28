package com.gymbuddy.backgymbuddy.admin.mission.service;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.repository.BiRepository;
import com.gymbuddy.backgymbuddy.admin.history.domain.History;
import com.gymbuddy.backgymbuddy.admin.history.repository.HistoryRepository;
import com.gymbuddy.backgymbuddy.admin.mission.domain.Mission;
import com.gymbuddy.backgymbuddy.admin.mission.domain.MissionDto;
import com.gymbuddy.backgymbuddy.admin.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final HistoryRepository historyRepository;
    private final BiRepository biRepository;

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Mission findOne(Long id) {
        return missionRepository.findById(id).get();
    }

    public Map<String, Object> findAllByMap() {
        List<History> histories = historyRepository.findAll();
        List<BusinessIdentity> biList = biRepository.findAll();
        List<Mission> mission = findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("historyList", histories);
        result.put("biList", biList);
        result.put("missionList", mission);
        return result;
    }

    @Transactional
    public Long save(MissionDto dto) {
        Mission mission = new Mission();
        if (dto.getContents() != null) {
            mission.setContents(dto.getContents());
        }
        if (dto.getImgName1() != null) {
            mission.setImgName1(dto.getImgName1());
        }
        if (dto.getImgPath1() != null) {
            mission.setImgPath1(dto.getImgPath1());
        }
        if (dto.getImgName2() != null) {
            mission.setImgName2(dto.getImgName2());
        }
        if (dto.getImgPath2() != null) {
            mission.setImgPath2(dto.getImgPath2());
        }
        if (dto.getImgName3() != null) {
            mission.setImgName3(dto.getImgName3());
        }
        if (dto.getImgPath3() != null) {
            mission.setImgPath3(dto.getImgPath3());
        }

        missionRepository.save(mission);
        return mission.getId();
    }

    @Transactional
    public void update(Long id, MissionDto dto) {
        Mission mission = findOne(id);
        if (dto.getContents() != null) {
            mission.setContents(dto.getContents());
        }
        if (dto.getImgPath1() != null) {
            mission.setImgPath1(dto.getImgPath1());
        }
        if (dto.getImgName1() != null) {
            mission.setImgName1(dto.getImgName1());
        }
        if (dto.getImgPath2() != null) {
            mission.setImgPath2(dto.getImgPath2());
        }
        if (dto.getImgName2() != null) {
            mission.setImgName2(dto.getImgName2());
        }
        if (dto.getImgPath3() != null) {
            mission.setImgPath3(dto.getImgPath3());
        }
        if (dto.getImgName3() != null) {
            mission.setImgName3(dto.getImgName3());
        }
    }

    @Transactional
    public void delete(Long id) {
        missionRepository.deleteById(id);
    }
}
