package com.gymbuddy.backgymbuddy.admin.question.service;

import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionCommentDto;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionDto;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionCommentService questionCommentService;

    public List<Question> findAll(int page) {
        return questionRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public Question findOne(Long id) {
        Question question = questionRepository.findById(id).get();
        List<QuestionComment> commentList = questionCommentService.findAllByQuestionId(id);
        question.setQuestionCommentList(commentList);
        return question;
    }

    public QuestionDto findOneByDto(Long id) {
        Question question = findOne(id);
        return questionToDto(question);
    }

    private QuestionDto questionToDto(Question question) {
        QuestionDto dto = new QuestionDto();

        if (question.getId() != null) {
            dto.setId(question.getId());
        }
        if (question.getCategoryId() != null) {
            dto.setCategoryId(question.getCategoryId());
        }
        if (question.getTitle() != null) {
            dto.setTitle(question.getTitle());
        }
        if (question.getContents() != null) {
            dto.setContents(question.getContents());
        }
        if (question.getImgName1() != null) {
            dto.setImgName1(question.getImgName1());
        }
        if (question.getImgPath1() != null) {
            dto.setImgPath1(question.getImgPath1());
        }
        if (question.getImgName2() != null) {
            dto.setImgName2(question.getImgName2());
        }
        if (question.getImgPath2() != null) {
            dto.setImgPath2(question.getImgPath2());
        }
        if (question.getImgName3() != null) {
            dto.setImgName3(question.getImgName3());
        }
        if (question.getImgPath3() != null) {
            dto.setImgPath3(question.getImgPath3());
        }

        if (!question.getQuestionCommentList().isEmpty()) {
            List<QuestionCommentDto> commentDtoList = new ArrayList<>();
            for (QuestionComment comment : question.getQuestionCommentList()) {
                QuestionCommentDto commentDto = new QuestionCommentDto();
                if (comment.getId() != null) {
                    commentDto.setId(comment.getId());
                }
                if (comment.getContents() != null) {
                    commentDto.setContents(comment.getContents());
                }
                commentDtoList.add(commentDto);
            }
            dto.setCommentList(commentDtoList);
        }
        return dto;
    }

    /**
     * 회원 id로 작성한 문의글 전체 가져오기
     * id = 회원 id
     */
    public List<Question> findAllByUser(String create_id, int page) {
        return questionRepository.findQuestionListByCreateId(create_id, PageRequest.of(page, 10)).getContent();
    }

    @Transactional
    public Long save(QuestionDto dto) {
        Question question = new Question();
        if (dto.getTitle() != null) {
            question.setTitle(dto.getTitle());
        }
        if (dto.getContents() != null) {
            question.setContents(dto.getContents());
        }
        if (dto.getCategoryId() != null) {
            question.setCategoryId(dto.getCategoryId());
        }
        if (dto.getImgName1() != null) {
            question.setImgName1(dto.getImgName1());
        }
        if (dto.getImgPath1() != null) {
            question.setImgPath1(dto.getImgPath1());
        }
        if (dto.getImgName2() != null) {
            question.setImgName2(dto.getImgName2());
        }
        if (dto.getImgPath2() != null) {
            question.setImgPath2(dto.getImgPath2());
        }
        if (dto.getImgName3() != null) {
            question.setImgName3(dto.getImgName3());
        }
        if (dto.getImgPath3() != null) {
            question.setImgPath3(dto.getImgPath3());
        }

        questionRepository.save(question);
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
        if (dto.getCategoryId() != null ) {
            question.setCategoryId(dto.getCategoryId());
        }
        if (!question.getImgPath1().equals(dto.getImgPath1())) {
            question.setImgPath1(dto.getImgPath1());
        }
        if (!question.getImgName1().equals(dto.getImgName1())) {
            question.setImgName1(dto.getImgName1());
        }
        if (!question.getImgPath2().equals(dto.getImgPath2())) {
            question.setImgPath2(dto.getImgPath2());
        }
        if (!question.getImgName2().equals(dto.getImgName2())) {
            question.setImgName2(dto.getImgName2());
        }
        if (!question.getImgPath3().equals(dto.getImgPath3())) {
            question.setImgPath3(dto.getImgPath3());
        }
        if (!question.getImgName3().equals(dto.getImgName3())) {
            question.setImgName3(dto.getImgName3());
        }
    }

    @Transactional
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
