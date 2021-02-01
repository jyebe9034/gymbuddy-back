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
import java.util.Objects;

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
    public void update(Long id, Map<String, Object> param) {
        ColumnWriter columnWriter = cwRepository.findById(id).get();
        if (param.get("contents") != null) {
            columnWriter.setContents(Objects.toString(param.get("contents")));
        }
    }

    @Transactional
    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            cwRepository.deleteById(idL);
        }
    }
}
