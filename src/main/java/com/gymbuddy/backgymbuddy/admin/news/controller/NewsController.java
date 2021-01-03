package com.gymbuddy.backgymbuddy.admin.news.controller;

import com.gymbuddy.backgymbuddy.admin.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
}
