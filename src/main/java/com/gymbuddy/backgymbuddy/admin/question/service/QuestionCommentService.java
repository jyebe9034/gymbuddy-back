package com.gymbuddy.backgymbuddy.admin.question.service;

import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionCommentDto;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionCommentRepository;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final QuestionRepository qr;

    public List<QuestionComment> findAll(Long id) {
        return questionCommentRepository.findQuestionCommentsById(id);
    }

    public QuestionComment findOne(Long id) {
        return questionCommentRepository.findById(id).get();
    }

    @Transactional
    public Long save(Long id, QuestionCommentDto dto) {
        QuestionComment comment = new QuestionComment();

        Optional<Question> findQuestion = qr.findById(id);

        comment.setQuestion(findQuestion.get());
        comment.setContents(dto.getContents());
        comment.setCreateDate(LocalDateTime.now());
        comment.setUpdateDate(LocalDateTime.now());

        questionCommentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void update(Long id, QuestionCommentDto dto) {
        QuestionComment comment = findOne(id);
        if (dto.getContents() != null) {
            comment.setContents(dto.getContents());
        }
        comment.setUpdateDate(LocalDateTime.now());
    }

    @Transactional
    public void delete(Long id) {
        questionCommentRepository.deleteById(id);
    }
}
