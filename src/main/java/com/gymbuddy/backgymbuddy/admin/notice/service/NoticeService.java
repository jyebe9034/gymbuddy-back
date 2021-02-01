package com.gymbuddy.backgymbuddy.admin.notice.service;

import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
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
    public Long save(Notice notice) {
        noticeRepository.save(notice);
        return notice.getId();
    }

    @Transactional
    public void update(Long id, Map<String, Object> param) {
        Notice notice = noticeRepository.findById(id).get();
        if (param.get("title") != null) {
            notice.setTitle(Objects.toString(param.get("title")));
        }
        if (param.get("contents") != null) {
            notice.setContents(Objects.toString(param.get("contents")));
        }
    }

    @Transactional
    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            noticeRepository.deleteById(idL);
        }
    }
}
