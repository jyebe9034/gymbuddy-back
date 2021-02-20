package com.gymbuddy.backgymbuddy.admin.column.service;

import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.domain.ColumnsDto;
import com.gymbuddy.backgymbuddy.admin.column.repository.ColumnRepository;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.repository.CWRepository;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final CWRepository cwRepository;

    public List<Columns> findAllForMain() {
        return columnRepository.findTop9ByOrderByIdDesc();
    }

    public List<Columns> findAll(int page) {
        return columnRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public Columns findOne(Long id) {
        return columnRepository.findById(id).get();
    }

    public Long save(ColumnsDto columns) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        // 컬럼 작성자 조회
        ColumnWriter columnWriter = cwRepository.findById(columns.getColumnWriterId()).get();

        Columns entity = new Columns();
        entity.setTitle(columns.getTitle());
        entity.setContents(columns.getContents());
        entity.setColumnWriter(columnWriter);
        entity.setImgPath(columns.getImgPath());
        entity.setImgName(columns.getImgName());
        entity.setMainYn(columns.getMainYn());
        entity.setCreateDate(LocalDateTime.now());
//        entity.setCreateId(loginId);
        entity.setUpdateDate(LocalDateTime.now());
//        entity.setUpdateId(loginId);

        columnRepository.save(entity);
        return entity.getId();
    }

    public void update(Long id, ColumnsDto column) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Columns origin = findOne(id);
        if (column.getTitle() != null) {
            origin.setTitle(column.getTitle());
        }
        if (column.getContents() != null) {
            origin.setTitle(column.getContents());
        }
        if (column.getColumnWriterId() != null) {
            ColumnWriter columnWriter = cwRepository.findById(column.getColumnWriterId()).get();
            origin.setColumnWriter(columnWriter);
        }
        if (column.getImgPath() != null) {
            origin.setImgPath(column.getImgPath());
        }
        if (column.getImgName() != null) {
            origin.setImgName(column.getImgName());
        }
        if (column.getMainYn() != null) {
            origin.setMainYn(column.getMainYn());
        }
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
