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
        if (columns.getTitle() != null) {
            entity.setTitle(columns.getTitle());
        }
        if (columns.getContents() != null) {
            entity.setContents(columns.getContents());
        }
        if (columnWriter != null) {
            entity.setColumnWriter(columnWriter);
        }
        if (columns.getImgPath() != null) {
            entity.setImgPath(columns.getImgPath());
        }
        if (columns.getImgName() != null) {
            entity.setImgName(columns.getImgName());
        }
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
        if (origin.getTitle() != null && !origin.getTitle().equals(column.getTitle())) {
            origin.setTitle(column.getTitle());
        }
        if (origin.getContents() != null && !origin.getContents().equals(column.getContents())) {
            origin.setContents(column.getContents());
        }
        if (origin.getColumnWriter() != null && !origin.getColumnWriter().equals(column.getColumnWriterId())) {
            ColumnWriter columnWriter = cwRepository.findById(column.getColumnWriterId()).get();
            origin.setColumnWriter(columnWriter);
        }
        if (origin.getImgPath() != null && !origin.getImgPath().equals(column.getImgPath())) {
            origin.setImgPath(column.getImgPath());
        }
        if (origin.getImgName() != null && !origin.getImgName().equals(column.getImgName())) {
            origin.setImgName(column.getImgName());
        }
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
