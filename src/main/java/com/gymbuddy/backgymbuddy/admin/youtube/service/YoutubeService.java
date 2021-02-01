package com.gymbuddy.backgymbuddy.admin.youtube.service;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
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
    public Long save(Youtube youtube) {
        youtubeRepository.save(youtube);
        return youtube.getId();
    }

    @Transactional
    public void update(Long id, Map<String, Object> param) {
        Youtube youtube = youtubeRepository.findById(id).get();
        if (param.get("uploadDate") != null) {
            youtube.setUploadDate(Objects.toString(param.get("uploadDate")));
        }
        if (param.get("title") != null) {
            youtube.setTitle(Objects.toString(param.get("title")));
        }
        if (param.get("contents") != null) {
            youtube.setContents(Objects.toString(param.get("contents")));
        }
        if (param.get("link") != null) {
            youtube.setLink(Objects.toString(param.get("link")));
        }
    }

    @Transactional
    public void delete(List<Integer> ids) {
        for (int id : ids) {
            long idL = new Long(id);
            youtubeRepository.deleteById(idL);
        }
    }
}
