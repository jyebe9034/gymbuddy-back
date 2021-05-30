package com.gymbuddy.backgymbuddy.admin.newsletter.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.NewsletterDto;
import com.gymbuddy.backgymbuddy.admin.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    /**
     * 뉴스레터 구독 이메일 전체 조회
     */
    public List<Newsletter> findAll(int page) {
        return newsletterRepository.findAll(PageRequest.of(page, 20, Sort.by("id").descending())).getContent();
    }

    /**
     * 뉴스레터 구독 이메일 전체 갯수 조회
     */
    public int selectTotalCount() {
        return newsletterRepository.findAll().size();
    }

    /**
     * 뉴스레터 구독 이메일 등록
     */
    @Transactional
    public Long save(NewsletterDto dto) {
        Newsletter newsletter = new Newsletter();
        if (dto.getEmail() != null) {
            newsletter.setEmail(dto.getEmail());
        }  else {
            throw new DMException("이메일을 입력해주세요.");
        }
        newsletterRepository.save(newsletter);
        return newsletter.getId();
    }

    /**
     * 뉴스레터 구독 이메일 삭제
     */
    @Transactional
    public void delete(Long id) {
        newsletterRepository.deleteById(id);
    }

    /**
     * 뉴스레터 구독 이메일 등록
     */
    @Transactional
    public List<Newsletter> search(String start, String end) {
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Newsletter> searchList = newsletterRepository.findAllByCreateDateBetween(startDate, endDate);
        return searchList;
    }
}
