package com.gymbuddy.backgymbuddy.admin.columnWriter.service;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.repository.CWRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CWService {

    private final CWRepository CWRepository;

    public List<ColumnWriter> findAll() {
        return CWRepository.findAll();
    }

    public ColumnWriter findOne(Long id) {
        return CWRepository.findById(id).get();
    }

    @Transactional
    public Long save(ColumnWriter columnWriter) {
        CWRepository.save(columnWriter);
        return columnWriter.getId();
    }

    @Transactional
    public void update(Long id, String contents) {
        ColumnWriter columnWriter = CWRepository.findById(id).get();
        columnWriter.setContents(contents);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedCount = CWRepository.deleteByIdIn(ids); // 삭제된 row 수
        if (ids.size() == deletedCount.intValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
