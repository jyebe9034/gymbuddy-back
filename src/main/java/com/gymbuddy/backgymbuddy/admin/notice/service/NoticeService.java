package com.gymbuddy.backgymbuddy.admin.notice.service;

import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> findAll() {
        return noticeRepository.findAll();
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
    public Long save(Notice notice) {
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Transactional
    public void update(Long id, String title, String contents) {
        Notice notice = noticeRepository.findById(id).get();
        notice.setTitle(title);
        notice.setContents(contents);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedRow = noticeRepository.deleteByIdIn(ids);
        if (ids.size() == deletedRow.intValue()) {
            return 1;
        }
        return 0;
    }
}
