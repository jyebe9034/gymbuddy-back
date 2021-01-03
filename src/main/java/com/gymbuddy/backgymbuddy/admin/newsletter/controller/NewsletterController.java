package com.gymbuddy.backgymbuddy.admin.newsletter.controller;

import com.gymbuddy.backgymbuddy.admin.newsletter.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsletterController {

    private final NewsletterService newsletterService;
}
