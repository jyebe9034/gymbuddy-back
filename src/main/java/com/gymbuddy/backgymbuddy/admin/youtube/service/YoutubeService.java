package com.gymbuddy.backgymbuddy.admin.youtube.service;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.YoutubeDto;
import com.gymbuddy.backgymbuddy.admin.youtube.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoutubeService {

    private final YoutubeRepository youtubeRepository;

    public int selectTotalCount() {
        return youtubeRepository.findAll().size();
    }

    public List<Youtube> findAllForMain() {
        return youtubeRepository.findAll(PageRequest.of(0, 9, Sort.by("id").descending())).getContent();
    }

    public List<Youtube> findAll(int page) {
        return youtubeRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    public List<Youtube> findAllForUser(int page) {
        return youtubeRepository.findAll(PageRequest.of(page, 15, Sort.by("id").descending())).getContent();
    }

    public Youtube findOne(Long id) {
        return youtubeRepository.findById(id).get();
    }

    @Transactional
    public Long save(YoutubeDto youtube) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Youtube entity = new Youtube();
        if (youtube.getUploadDate() != null) {
            entity.setUploadDate(youtube.getUploadDate());
        }
        if (youtube.getTitle() != null) {
            entity.setTitle(youtube.getTitle());
        }
        if (youtube.getContents() != null) {
            entity.setContents(youtube.getContents());
        }
        if (youtube.getLink() != null) {
            entity.setLink(youtube.getLink());
        }
        if (youtube.getImgPath() != null) {
            entity.setImgPath(youtube.getImgPath());
        }
        if (youtube.getImgName() != null) {
            entity.setImgName(youtube.getImgName());
        }
        entity.setCreateDate(LocalDateTime.now());
//        entity.setCreateId(loginId);
        entity.setUpdateDate(LocalDateTime.now());
//        entity.setUpdateId(loginId);

        youtubeRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, YoutubeDto youtube) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Youtube origin = findOne(id);
        if (origin.getUploadDate() != null && !origin.getUploadDate().equals(youtube.getUploadDate())) {
            origin.setUploadDate(youtube.getUploadDate());
        }
        if (origin.getTitle() != null) {
            origin.setTitle(youtube.getTitle());
        }
        if (origin.getContents() != null) {
            origin.setContents(youtube.getContents());
        }
        if (origin.getLink() != null) {
            origin.setLink(youtube.getLink());
        }
        if (origin.getImgPath() != null) {
            origin.setImgPath(youtube.getImgPath());
        }
        if (origin.getImgName() != null) {
            origin.setImgName(youtube.getImgName());
        }
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        youtubeRepository.deleteById(id);
    }
}
