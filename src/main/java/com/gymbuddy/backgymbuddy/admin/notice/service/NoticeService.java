package com.gymbuddy.backgymbuddy.admin.notice.service;

import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.domain.NoticeDto;
import com.gymbuddy.backgymbuddy.admin.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        List<Notice> all = noticeRepository.findAll();
        List<Notice> fiveList = all.stream().sorted(Collections.reverseOrder()).limit(5).collect(Collectors.toList());
        return fiveList;
    }

    public Notice findOne(Long id) {
        return noticeRepository.findById(id).get();
    }

    @Transactional
    public Long save(NoticeDto notice) {
        Notice entity = new Notice();
        entity.setTitle(notice.getTitle());
        entity.setContents(notice.getContents());
        entity.setImgPath(notice.getImgPath());
        entity.setImgName(notice.getImgName());
        entity.setMainYn(notice.getMainYn());

        noticeRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, NoticeDto notice) {
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
        if (notice.getMainYn() != null) {
            origin.setMainYn(notice.getMainYn());
        }
    }

    @Transactional
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
