package com.gymbuddy.backgymbuddy.admin.main.controller;

import com.gymbuddy.backgymbuddy.admin.banner.domain.Banner;
import com.gymbuddy.backgymbuddy.admin.banner.service.BannerService;
import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import com.gymbuddy.backgymbuddy.admin.column.service.ColumnService;
import com.gymbuddy.backgymbuddy.admin.news.domain.News;
import com.gymbuddy.backgymbuddy.admin.news.service.NewsService;
import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import com.gymbuddy.backgymbuddy.admin.notice.service.NoticeService;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.MAIN_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController extends BaseController {

    private final BannerService bannerService;
    private final YoutubeService youtubeService;
    private final NoticeService noticeService;
    private final NewsService newsService;
    private final ColumnService columnService;
    // TODO 나중에 굿즈 & 프로그램 추가
    
    @GetMapping(MAIN_PREFIX + "/all")
    public ResponseEntity<Map<String, Object>> selectAllMainInfo() {
        List<Banner> bannerList = bannerService.findAll();
        List<Youtube> youtubeList = youtubeService.findAllForMain();
        List<Notice> noticeList = noticeService.findAllForMain();
        List<News> newsList = newsService.findAllForMain();
        List<Columns> columnsList = columnService.findAllForMain();

        Map<String, Object> result = new HashMap<>();
        result.put("bannerList", bannerList);
        result.put("youtubeList", youtubeList);
        result.put("noticeList", noticeList);
        result.put("newsList", newsList);
        result.put("columnsList", columnsList);

        return createResponseEntity(true, result);
    }

}
