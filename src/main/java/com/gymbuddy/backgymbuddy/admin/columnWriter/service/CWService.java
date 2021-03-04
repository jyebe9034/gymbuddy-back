package com.gymbuddy.backgymbuddy.admin.columnWriter.service;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import com.gymbuddy.backgymbuddy.admin.columnWriter.repository.CWRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class CWService {

    private final CWRepository cwRepository;

    public int selectTotalCount() {
        List<ColumnWriter> all = cwRepository.findAll();
        return all.size();
    }

    public List<ColumnWriter> findAll() {
        return cwRepository.findAll();
    }

    public List<ColumnWriter> findAll(int page) {
        return cwRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    public ColumnWriter findOne(Long id) {
        // TODO Optional일 경우 null이면 customException을 날리도록 해야함..나중에 할 것.
        return cwRepository.findById(id).get();
    }

    @Transactional
    public Long save(ColumnWriter columnWriter) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        columnWriter.setCreateId(loginId);
        columnWriter.setUpdateId(loginId);

        cwRepository.save(columnWriter);
        return columnWriter.getId();
    }

    @Transactional
    public void update(Long id, ColumnWriter columnWriter) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        ColumnWriter origin = cwRepository.findById(id).get();
        if (origin.getName() != null) {
            origin.setName(columnWriter.getName());
        }
        if (origin.getJob() != null) {
            origin.setJob(columnWriter.getJob());
        }
        if (origin.getContents() != null) {
            origin.setContents(columnWriter.getContents());
        }
        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            cwRepository.deleteById(idL);
        }
    }
}
