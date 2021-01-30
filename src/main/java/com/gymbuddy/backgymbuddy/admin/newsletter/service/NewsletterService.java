package com.gymbuddy.backgymbuddy.admin.newsletter.service;

import com.gymbuddy.backgymbuddy.admin.newsletter.domain.Newsletter;
import com.gymbuddy.backgymbuddy.admin.newsletter.repository.NewsletterRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.NEWSLETTER_PREFIX;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRespository newsletterRespository;

    public List<Newsletter> findAll() {
        return newsletterRespository.findAll();
    }

    @Transactional
    public Long save(Newsletter newsletter) {
        newsletterRespository.save(newsletter);
        return newsletter.getId();
    }

    @Transactional
    public Long delete(Long id) {
        newsletterRespository.deleteById(id);
        return id;
    }
}
