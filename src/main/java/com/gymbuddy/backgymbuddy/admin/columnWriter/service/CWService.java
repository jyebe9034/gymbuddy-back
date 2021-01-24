package com.gymbuddy.backgymbuddy.admin.columnWriter.service;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.repository.CWRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CWService {

    private final CWRepository CWRepository;

    public List<ColumnWriter> selectColumnWriterList() {
        return CWRepository.findAll();
    }

    public ColumnWriter selectColumnWriterDetail(Long columnWriterId) {
        return CWRepository.findById(columnWriterId).get();
    }
}
