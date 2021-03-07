package com.gymbuddy.backgymbuddy.admin.notice.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.domain.NoticeDto;
import com.gymbuddy.backgymbuddy.admin.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public int selectTotalCount() {
        return noticeRepository.findAll().size();
    }

    public List<Notice> findAll(int page) {
        return noticeRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    public List<Notice> findAllForMain() {
        return noticeRepository.findAll(PageRequest.of(0, 5, Sort.by("id").descending())).getContent();
    }

    public Notice findOne(Long id) {
        Optional<Notice> byId = noticeRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 공지사항입니다.");
        }
        return byId.get();
    }

    @Transactional
    public Long save(NoticeDto notice) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Notice entity = new Notice();
        if (notice.getTitle() != null) {
            entity.setTitle(notice.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (notice.getContents() != null) {
            entity.setContents(notice.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        if (notice.getImgPath() != null) {
            entity.setImgPath(notice.getImgPath());
        }
        if (notice.getImgName() != null) {
            entity.setImgName(notice.getImgName());
        }
        entity.setCreateId(loginId);
        entity.setUpdateId(loginId);

        noticeRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, NoticeDto notice) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Notice origin = findOne(id);
        if (notice.getTitle() != null) {
            origin.setTitle(notice.getTitle());
        }
        if (notice.getContents() != null) {
            origin.setContents(notice.getContents());
        }
        if (notice.getImgPath() != null) {
            origin.setImgPath(notice.getImgPath());
        }
        if (notice.getImgName() != null) {
            origin.setImgName(notice.getImgName());
        }
        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

}
