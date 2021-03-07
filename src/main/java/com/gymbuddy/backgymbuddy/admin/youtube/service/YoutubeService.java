package com.gymbuddy.backgymbuddy.admin.youtube.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
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
import java.util.*;

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
        Optional<Youtube> byId = youtubeRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 유튜브입니다.");
        }
        return byId.get();
    }

    @Transactional
    public Long save(YoutubeDto youtube) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Youtube entity = new Youtube();
        if (youtube.getUploadDate() != null) {
            entity.setUploadDate(youtube.getUploadDate());
        } else {
            throw new DMException("작성일자를 입력해주세요.");
        }
        if (youtube.getTitle() != null) {
            entity.setTitle(youtube.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (youtube.getContents() != null) {
            entity.setContents(youtube.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        if (youtube.getLink() != null) {
            entity.setLink(youtube.getLink());
        } else {
            throw new DMException("링크를 입력해주세요.");
        }
        if (youtube.getImgPath() != null) {
            entity.setImgPath(youtube.getImgPath());
        } else {
            throw new DMException("이미지를 등록해주세요.");
        }
        if (youtube.getImgName() != null) {
            entity.setImgName(youtube.getImgName());
        } else {
            throw new DMException("이미지를 등록해주세요.");
        }
        entity.setCreateId(loginId);
        entity.setUpdateId(loginId);

        youtubeRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, YoutubeDto youtube) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Youtube origin = findOne(id);
        if (youtube.getUploadDate() != null) {
            origin.setUploadDate(youtube.getUploadDate());
        }
        if (youtube.getTitle() != null) {
            origin.setTitle(youtube.getTitle());
        }
        if (youtube.getContents() != null) {
            origin.setContents(youtube.getContents());
        }
        if (youtube.getLink() != null) {
            origin.setLink(youtube.getLink());
        }
        if (youtube.getImgPath() != null) {
            origin.setImgPath(youtube.getImgPath());
        }
        if (youtube.getImgName() != null) {
            origin.setImgName(youtube.getImgName());
        }
        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        youtubeRepository.deleteById(id);
    }
}
