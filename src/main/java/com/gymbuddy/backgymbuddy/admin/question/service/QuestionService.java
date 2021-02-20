package com.gymbuddy.backgymbuddy.admin.question.service;

import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionDto;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionCommentRepository;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository qr;
    private final QuestionCommentService questionCommentService;

    public List<Question> findAll(int page) {
        return qr.findAll(PageRequest.of(page, 10)).getContent();
    }

    public Question findOne(Long id) {
        return qr.findById(id).get();
    }

    public Map<String, Object> findDetail(Long id) {
        Question question = qr.findById(id).get();
        List<QuestionComment> commentList = questionCommentService.findAll(id);

        Map<String, Object> result = new HashMap<>();
        result.put("question", question);
        result.put("commentList", commentList);
        return result;
    }

    /**
     * 회원 id로 작성한 문의글 전체 가져오기
     * id = 회원 id
     */
    public List<Question> findAllByUser(Long id, int page) {
        return qr.findQuestionListById(id, PageRequest.of(page, 10)).getContent();
    }

    @Transactional
    public Long save(QuestionDto dto) {
        Question question = new Question();
        question.setTitle(dto.getTitle());
        question.setContents(dto.getContents());
        question.setImgName1(dto.getImgName1());
        question.setImgPath1(dto.getImgPath1());
        question.setImgName2(dto.getImgName2());
        question.setImgPath2(dto.getImgPath2());
        question.setImgName3(dto.getImgName3());
        question.setImgPath3(dto.getImgPath3());
        question.setCreateDate(LocalDateTime.now());
        question.setUpdateDate(LocalDateTime.now());

        qr.save(question);
        return question.getId();
    }

    @Transactional
    public void update(Long id, QuestionDto dto) {
        Question question = findOne(id);
        if (dto.getTitle() != null) {
            question.setTitle(dto.getTitle());
        }
        if (dto.getContents() != null) {
            question.setContents(dto.getContents());
        }
        if (dto.getImgPath1() != null) {
            question.setImgPath1(dto.getImgPath1());
        }
        if (dto.getImgName1() != null) {
            question.setImgName1(dto.getImgName1());
        }
        if (dto.getImgPath2() != null) {
            question.setImgPath2(dto.getImgPath2());
        }
        if (dto.getImgName2() != null) {
            question.setImgName2(dto.getImgName2());
        }
        if (dto.getImgPath3() != null) {
            question.setImgPath3(dto.getImgPath3());
        }
        if (dto.getImgName3() != null) {
            question.setImgName3(dto.getImgName3());
        }
        question.setUpdateDate(LocalDateTime.now());
    }

    @Transactional
    public void delete(Long id) {
        qr.deleteById(id);
    }
}
