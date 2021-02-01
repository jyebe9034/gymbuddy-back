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
import java.util.Map;
import java.util.Objects;

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

    public void update(Long id, Map<String, Object> param) {
        Columns columns = columnRepository.findById(id).get();
        if (param.get("title") != null) {
            columns.setTitle(Objects.toString(param.get("title")));
        }
        if (param.get("contents") != null) {
            columns.setTitle(Objects.toString(param.get("contents")));
        }
        // 컬럼 작성자의 정보를 꼭 다 넘겨야 하는지..
        if (param.get("columnWriter") != null) {
            columns.setColumnWriter((ColumnWriter) param.get("columnWriter"));
        }
    }

    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            columnRepository.deleteById(idL);
        }
    }
}
