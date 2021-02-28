package com.gymbuddy.backgymbuddy.admin.newsletter.service;

import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.NewsletterDto;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.NewsletterSearch;
import com.gymbuddy.backgymbuddy.admin.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public List<Newsletter> findAll() {
        return newsletterRepository.findAll();
    }

    @Transactional
    public Long save(NewsletterDto dto) {
        Newsletter newsletter = new Newsletter();
        if (dto.getEmail() != null) {
            newsletter.setEmail(dto.getEmail());
        }
        newsletterRepository.save(newsletter);
        return newsletter.getId();
    }

    @Transactional
    public void delete(Long id) {
        newsletterRepository.deleteById(id);
    }

    @Transactional
    public List<Newsletter> search(NewsletterSearch search) {
        String start = search.getStart();
        String end = search.getEnd();
        LocalDateTime startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Newsletter> searchList = newsletterRepository.findAllByCreateDateBetween(startDate, endDate);
        return searchList;
    }
}
