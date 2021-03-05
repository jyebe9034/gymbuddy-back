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
import java.util.HashMap;
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
    public Map<String, Object> save(YoutubeDto youtube) {
        // 현재 로그인한 아이디 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Map<String, Object> result = new HashMap<>();

        Youtube entity = new Youtube();
        if (youtube.getUploadDate() != null) {
            entity.setUploadDate(youtube.getUploadDate());
        }
        if (youtube.getTitle() != null) {
            entity.setTitle(youtube.getTitle());
        } else {
            result.put("successYn", "N");
            result.put("msg", "제목을 입력해주세요.");
            return result;
        }
        if (youtube.getContents() != null) {
            entity.setContents(youtube.getContents());
        } else {
            result.put("successYn", "N");
            result.put("msg", "내용을 입력해주세요.");
            return result;
        }
        if (youtube.getLink() != null) {
            entity.setLink(youtube.getLink());
        } else {
            result.put("successYn", "N");
            result.put("msg", "링크를 입력해주세요.");
            return result;
        }
        if (youtube.getImgPath() != null) {
            entity.setImgPath(youtube.getImgPath());
        } else {
            result.put("successYn", "N");
            result.put("msg", "이미지를 등록해주세요.");
            return result;
        }
        if (youtube.getImgName() != null) {
            entity.setImgName(youtube.getImgName());
        } else {
            result.put("successYn", "N");
            result.put("msg", "이미지를 등록해주세요.");
            return result;
        }
        entity.setCreateId(loginId);
        entity.setUpdateId(loginId);

        youtubeRepository.save(entity);
        result.put("successYn", "N");
        result.put("id", entity.getId());
        return result;
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
        if (youtube.getTitle() != null && !origin.getTitle().equals(youtube.getTitle())) {
            origin.setTitle(youtube.getTitle());
        }
        if (youtube.getContents() != null && !origin.getContents().equals(youtube.getContents())) {
            origin.setContents(youtube.getContents());
        }
        if (youtube.getLink() != null && !origin.getLink().equals(youtube.getLink())) {
            origin.setLink(youtube.getLink());
        }
        if (youtube.getImgPath() != null && !origin.getImgPath().equals(youtube.getImgPath())) {
            origin.setImgPath(youtube.getImgPath());
        }
        if (youtube.getImgName() != null && !origin.getImgName().equals(youtube.getImgName())) {
            origin.setImgName(youtube.getImgName());
        }
        origin.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        youtubeRepository.deleteById(id);
    }
}
