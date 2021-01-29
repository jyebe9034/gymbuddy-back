package com.gymbuddy.backgymbuddy.admin.news.service;

import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
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
    public Long save(News news) {
        newsRepository.save(news);
        return news.getId();
    }

    @Transactional
    public void update(Long id, String title, String contents) {
        News news = newsRepository.findById(id).get();
        news.setTitle(title);
        news.setContents(contents);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedRows = newsRepository.deleteByIdIn(ids);
        if (ids.size() == deletedRows.intValue()) {
            return 1;
        }
        return 0;
    }
}
