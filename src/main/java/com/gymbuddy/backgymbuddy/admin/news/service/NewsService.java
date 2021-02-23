package com.gymbuddy.backgymbuddy.admin.news.service;

import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.domain.NewsDto;
import com.gymbuddy.backgymbuddy.admin.news.repository.NewsRepository;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

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
public class NewsService {

    private final NewsRepository newsRepository;

    public List<News> findAll(int page) {
        return newsRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    public List<News> findAllForMain() {
        return newsRepository.findAll(PageRequest.of(0, 5, Sort.by("id").descending())).getContent();
    }

    public News findOne(Long id) {
        return newsRepository.findById(id).get();
    }

    @Transactional
    public Long save(NewsDto news) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        News entity = new News();
        if (news.getTitle() != null) {
            entity.setTitle(news.getTitle());
        }
        if (news.getContents() != null) {
            entity.setContents(news.getContents());
        }
        if (news.getImgPath() != null) {
            entity.setImgPath(news.getImgPath());
        }
        if (news.getImgName() != null) {
            entity.setImgName(news.getImgName());
        }
//        entity.setCreateId(loginId);
//        entity.setUpdateId(loginId);

        newsRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, NewsDto news) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        News origin = findOne(id);
        if (origin.getTitle() != null) {
            origin.setTitle(news.getTitle());
        }
        if (origin.getContents() != null) {
            origin.setContents(news.getContents());
        }
        if (origin.getImgPath() != null && !origin.getImgPath().equals(news.getImgPath())) {
            origin.setImgPath(news.getImgPath());
        }
        if (origin.getImgName() != null && !origin.getImgName().equals(news.getImgName())) {
            origin.setImgName(news.getImgName());
        }
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
