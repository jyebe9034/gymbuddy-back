package com.gymbuddy.backgymbuddy.admin.question.service;

import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionCommentDto;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionCommentRepository;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final QuestionRepository questionRepository;

    public List<QuestionComment> findAll(Long id) {
        return questionCommentRepository.findAll();
    }

    public List<QuestionComment> findAllByQuestionId(Long id) {
        return questionCommentRepository.findByQuestionId(id);
    }

    public QuestionComment findOne(Long id) {
        Optional<QuestionComment> byId = questionCommentRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 문의 댓글입니다.");
        }
        return byId.get();
    }

    private QuestionCommentDto commentToDto(QuestionComment comment) {
        QuestionCommentDto dto = new QuestionCommentDto();

        if (comment.getId() != null) {
            dto.setId(comment.getId());
        }
        if (comment.getContents() != null) {
            dto.setContents(dto.getContents());
        }
        if (comment.getCreateId() != null) {
            dto.setCreateId(dto.getCreateId());
        }
        if (comment.getCreateDate() != null) {
            dto.setCreateDate(dto.getCreateDate());
        }
        return dto;
    }

    @Transactional
    public Long save(Long id, QuestionCommentDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        QuestionComment comment = new QuestionComment();
        Optional<Question> findQuestion = questionRepository.findById(id);
        if (findQuestion.get() != null) {
            comment.setQuestion(findQuestion.get());

            if (dto.getContents() != null) {
                comment.setContents(dto.getContents());
            } else {
                throw new DMException("내용을 입력해주세요.");
            }
        }
        comment.setCreateId(loginId);
        comment.setUpdateId(loginId);

        questionCommentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void update(Long id, QuestionCommentDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        QuestionComment comment = findOne(id);
        if (!comment.getContents().equals(dto.getContents())) {
            comment.setContents(dto.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        comment.setUpdateId(loginId);
    }

    @Transactional
    public void delete(Long id) {
        questionCommentRepository.deleteById(id);
    }
}
