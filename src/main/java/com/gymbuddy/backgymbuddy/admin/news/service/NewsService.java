package com.gymbuddy.backgymbuddy.admin.news.service;

import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.domain.NewsDto;
import com.gymbuddy.backgymbuddy.admin.news.repository.NewsRepository;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
        return newsRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public List<News> findAllForMain() {
        List<News> list = newsRepository.findTop5ByOrderByIdDesc();
        return list;
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
        entity.setTitle(news.getTitle());
        entity.setContents(news.getContents());
        entity.setImgPath(news.getImgPath());
        entity.setImgName(news.getImgName());
        entity.setCreateDate(LocalDateTime.now());
//        entity.setCreateId(loginId);
        entity.setUpdateDate(LocalDateTime.now());
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
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
