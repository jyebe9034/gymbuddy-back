package com.gymbuddy.backgymbuddy.admin.news.service;

import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.domain.NewsDto;
import com.gymbuddy.backgymbuddy.admin.news.repository.NewsRepository;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<News> findAll(int page) {
        return newsRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public List<News> findAllForMain() {
        List<News> all = newsRepository.findAll();
        List<News> fiveList = all.stream().sorted(Collections.reverseOrder()).limit(5).collect(Collectors.toList());
        return fiveList;
    }

    public News findOne(Long id) {
        return newsRepository.findById(id).get();
    }

    @Transactional
    public Long save(NewsDto news) {
        News entity = new News();
        entity.setTitle(news.getTitle());
        entity.setContents(news.getContents());
        entity.setImgPath(news.getImgPath());
        entity.setImgName(news.getImgName());
        entity.setMainYn(news.getMainYn());

        newsRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, NewsDto news) {
        News origin = findOne(id);
        if (news.getTitle() != null) {
            origin.setTitle(news.getTitle());
        }
        if (news.getContents() != null) {
            origin.setContents(news.getContents());
        }
        if (news.getImgPath() != null) {
            origin.setImgPath(news.getImgPath());
        }
        if (news.getImgName() != null) {
            origin.setImgName(news.getImgName());
        }
        if (news.getMainYn() != null) {
            origin.setMainYn(news.getMainYn());
        }
    }

    @Transactional
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
