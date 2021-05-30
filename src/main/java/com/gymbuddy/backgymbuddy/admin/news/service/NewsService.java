package com.gymbuddy.backgymbuddy.admin.news.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
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
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    /**
     * 전체 대외뉴스 갯수 조회
     */
    public int selectTotalCount() {
        return newsRepository.findAll().size();
    }

    /**
     * 전체 대외뉴스 조회
     */
    public List<News> findAll(int page) {
        return newsRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    /**
     * 메인에 노출 될 전체 대외뉴스 조회
     */
    public List<News> findAllForMain() {
        return newsRepository.findAll(PageRequest.of(0, 5, Sort.by("id").descending())).getContent();
    }

    /**
     * 대외뉴스 한개 조회
     */
    public News findOne(Long id) {
        Optional<News> byId = newsRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 컬럼 작성자입니다.");
        }
        return byId.get();
    }

    /**
     * 대외뉴스 등록
     */
    @Transactional
    public Long save(NewsDto news) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        News entity = new News();
        if (news.getTitle() != null) {
            entity.setTitle(news.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (news.getContents() != null) {
            entity.setContents(news.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        if (news.getImgPath() != null) {
            entity.setImgPath(news.getImgPath());
        }
        if (news.getImgName() != null) {
            entity.setImgName(news.getImgName());
        }
        entity.setCreateId(loginId);
        entity.setUpdateId(loginId);

        newsRepository.save(entity);
        return entity.getId();
    }

    /**
     * 대외뉴스 수정
     */
    @Transactional
    public void update(Long id, NewsDto news) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

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
        origin.setUpdateId(loginId);
    }

    /**
     * 대외뉴스 삭제
     */
    @Transactional
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
