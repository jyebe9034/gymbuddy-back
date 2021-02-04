package com.gymbuddy.backgymbuddy.admin.youtube.service;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.domain.YoutubeDto;
import com.gymbuddy.backgymbuddy.admin.youtube.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoutubeService {

    private final YoutubeRepository youtubeRepository;

    public List<Youtube> findAll(int page) {
        return youtubeRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public Youtube findOne(Long id) {
        return youtubeRepository.findById(id).get();
    }

    @Transactional
    public Long save(YoutubeDto youtube) {
        Youtube entity = new Youtube();
        entity.setUploadDate(youtube.getUploadDate());
        entity.setTitle(youtube.getTitle());
        entity.setContents(youtube.getContents());
        entity.setLink(youtube.getLink());
        entity.setImgPath(youtube.getImgPath());
        entity.setImgName(youtube.getImgName());
        entity.setMainYn(youtube.getMainYn());

        youtubeRepository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void update(Long id, YoutubeDto youtube) {
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
    }

    @Transactional
    public void delete(Long id) {
        youtubeRepository.deleteById(id);
    }
}
