package com.gymbuddy.backgymbuddy.admin.column.service;

import com.gymbuddy.backgymbuddy.admin.column.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
}
