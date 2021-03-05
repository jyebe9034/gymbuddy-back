package com.gymbuddy.backgymbuddy.admin.column.service;

import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.domain.ColumnsDto;
import com.gymbuddy.backgymbuddy.admin.column.repository.ColumnRepository;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriterDto;
import com.gymbuddy.backgymbuddy.admin.columnWriter.repository.CWRepository;
import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final CWRepository cwRepository;

    public int selectTotalCount() {
        return columnRepository.findAll().size();
    }

    public List<Columns> findAllForMain() {
        return columnRepository.findAll(PageRequest.of(0, 9, Sort.by("id").descending())).getContent();
    }

    public List<Columns> findAll(int page) {
        List<Columns> list = columnRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
        return list;
    }

    public List<Columns> findAllForUser(int page) {
        List<Columns> list = columnRepository.findAll(PageRequest.of(page, 15, Sort.by("id").descending())).getContent();
        return list;
    }

    public Columns findOne(Long id) {
        Optional<Columns> byId = columnRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 컬럼입니다.");
        }
        return byId.get();
    }

    public ColumnWriter findOneCw(Long id) {
        Optional<ColumnWriter> byId = cwRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 컬럼 작성자입니다.");
        }
        return byId.get();
    }

    public ColumnsDto findOneDto(Long id) {
        Columns origin = findOne(id);
        ColumnWriter originCw = findOneCw(origin.getColumnWriter().getId());

        ColumnWriterDto columnWriter = new ColumnWriterDto();
        columnWriter.setId(originCw.getId());
        columnWriter.setName(originCw.getName());
        columnWriter.setJob(originCw.getJob());
        columnWriter.setContents(originCw.getContents());

        ColumnsDto column = new ColumnsDto();
        column.setId(origin.getId());
        column.setTitle(origin.getTitle());
        column.setContents(origin.getContents());
        column.setColumnWriter(columnWriter);
        column.setImgName(origin.getImgName());
        column.setImgPath(origin.getImgPath());

        return column;
    }

    @Transactional
    public Long save(ColumnsDto columns) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        // 컬럼 작성자 조회
        ColumnWriter columnWriter = findOneCw(columns.getColumnWriterId());

        Columns entity = new Columns();
        if (columns.getTitle() != null) {
            entity.setTitle(columns.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (columns.getContents() != null) {
            entity.setContents(columns.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        if (columnWriter != null) {
            entity.setColumnWriter(columnWriter);
        }
        if (columns.getImgPath() != null) {
            entity.setImgPath(columns.getImgPath());
        } else {
            throw new DMException("이미지를 등록해주세요.");
        }
        if (columns.getImgName() != null) {
            entity.setImgName(columns.getImgName());
        } else {
            throw new DMException("이미지를 등록해주세요.");
        }
        entity.setCreateId(loginId);
        entity.setUpdateId(loginId);

        columnRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, ColumnsDto column) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Columns origin = findOne(id);
        if (column.getTitle() != null && !origin.getTitle().equals(column.getTitle())) {
            origin.setTitle(column.getTitle());
        }
        if (column.getContents() != null && !origin.getContents().equals(column.getContents())) {
            origin.setContents(column.getContents());
        }
        if (column.getColumnWriter() != null && !origin.getColumnWriter().getId().equals(column.getColumnWriterId())) {
            ColumnWriter columnWriter = findOneCw(column.getColumnWriterId());
            origin.setColumnWriter(columnWriter);
        }
        if (column.getImgPath() != null && !origin.getImgPath().equals(column.getImgPath())) {
            origin.setImgPath(column.getImgPath());
        }
        if (column.getImgName() != null && !origin.getImgName().equals(column.getImgName())) {
            origin.setImgName(column.getImgName());
        }
        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
