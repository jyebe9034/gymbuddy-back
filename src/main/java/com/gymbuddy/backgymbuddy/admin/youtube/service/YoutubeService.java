package com.gymbuddy.backgymbuddy.admin.youtube.service;

import com.gymbuddy.backgymbuddy.admin.youtube.domain.Youtube;
import com.gymbuddy.backgymbuddy.admin.youtube.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoutubeService {

    private final YoutubeRepository youtubeRepository;

    public List<Youtube> findAll() {
        return youtubeRepository.findAll();
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
    public void update(Long id, String uploadDate, String title, String contents, String link) {
        Youtube youtube = youtubeRepository.findById(id).get();
        youtube.setUploadDate(uploadDate);
        youtube.setTitle(title);
        youtube.setContents(contents);
        youtube.setLink(link);
    }

    @Transactional
    public int delete(List<Long> ids) {
        Long deletedRows = youtubeRepository.deleteByIdIn(ids);
        if (ids.size() == deletedRows.intValue()) {
            return 1;
        }
        return 0;
    }
}
