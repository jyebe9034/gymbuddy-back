package com.gymbuddy.backgymbuddy.admin.notice.service;

import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.domain.NoticeDto;
import com.gymbuddy.backgymbuddy.admin.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> findAll(int page) {
        return noticeRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public List<Notice> findAllForMain() {
        List<Notice> list = noticeRepository.findTop5ByOrderByIdDesc();
        return list;
    }

    public Notice findOne(Long id) {
        return noticeRepository.findById(id).get();
    }

    @Transactional
    public Long save(NoticeDto notice) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Notice entity = new Notice();
        entity.setTitle(notice.getTitle());
        entity.setContents(notice.getContents());
        entity.setImgPath(notice.getImgPath());
        entity.setImgName(notice.getImgName());
        entity.setCreateDate(LocalDateTime.now());
//        entity.setCreateId(loginId);
        entity.setUpdateDate(LocalDateTime.now());
//        entity.setUpdateId(loginId);

        noticeRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, NoticeDto notice) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

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
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
