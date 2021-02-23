package com.gymbuddy.backgymbuddy.admin.newsletter.service;

import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.domain.NewsletterDto;
import com.gymbuddy.backgymbuddy.admin.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
