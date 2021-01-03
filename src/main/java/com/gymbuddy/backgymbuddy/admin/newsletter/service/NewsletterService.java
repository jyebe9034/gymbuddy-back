package com.gymbuddy.backgymbuddy.admin.newsletter.service;

import com.gymbuddy.backgymbuddy.admin.newsletter.repository.NewsletterRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRespository newsletterRespository;
}
