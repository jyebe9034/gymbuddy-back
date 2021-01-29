package com.gymbuddy.backgymbuddy.admin.columnWriter.service;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.repository.CWRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CWService {

    private final CWRepository cwRepository;

    public List<ColumnWriter> findAll(int page) {
        return cwRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public ColumnWriter findOne(Long id) {
        // TODO Optional일 경우 null이면 customException을 날리도록 해야함..나중에 할 것.
        return cwRepository.findById(id).get();
    }

    @Transactional
    public Long save(ColumnWriter columnWriter) {
        cwRepository.save(columnWriter);
        return columnWriter.getId();
    }

    @Transactional
    public void update(Long id, String contents) {
        ColumnWriter columnWriter = cwRepository.findById(id).get();
        columnWriter.setContents(contents);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedRows = cwRepository.deleteByIdIn(ids); // 삭제된 row 수
        if (ids.size() == deletedRows.intValue()) {
            return 1;
        }
        return 0;
    }
}
