package com.gymbuddy.backgymbuddy.admin.column.service;

import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.repository.ColumnRepository;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;

    public List<Columns> findAll(int page) {
        return columnRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public Columns findOne(Long id) {
        return columnRepository.findById(id).get();
    }

    public Long save(Columns columns) {
        columnRepository.save(columns);
        return columns.getId();
    }

    public void update(Long id, String title, String contents, ColumnWriter columnWriter) {
        Columns columns = columnRepository.findById(id).get();
        columns.setTitle(title);
        columns.setContents(contents);
        columns.setColumnWriter(columnWriter);
    }

    public int delete(List<Long> ids) {
        Long deletedRows = columnRepository.deleteByIdIn(ids);
        if (ids.size() == deletedRows.intValue()) {
            return 1;
        }
        return 0;
    }
}
