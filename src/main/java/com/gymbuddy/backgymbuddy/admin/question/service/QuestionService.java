package com.gymbuddy.backgymbuddy.admin.question.service;

import com.gymbuddy.backgymbuddy.admin.enums.category.QuestionEnum;
import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.question.domain.Question;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionComment;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionCommentDto;
import com.gymbuddy.backgymbuddy.admin.question.domain.QuestionDto;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionCommentRepository;
import com.gymbuddy.backgymbuddy.admin.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionCommentRepository qcRepository;
    private final QuestionCommentService questionCommentService;

    /**
     * 전체 1:1 문의글 조회(관리자)
     */
    public Map<String, Object> findAll(int page) {
        List<Question> questionList = questionRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
        List<QuestionDto> dtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto dto = questionToDto(question);
            dtoList.add(dto);
        }

        List<QuestionEnum> categoryList = categoryList();

        Map<String, Object> result = new HashMap<>();
        result.put("questionList", dtoList);
        result.put("categoryList", categoryList);
        return result;
    }

    /**
     * 문의글 카테고리 조회
     */
    public List<QuestionEnum> categoryList() {
        List<QuestionEnum> categoryList = new ArrayList<>();
        categoryList.add(QuestionEnum.PY);
        categoryList.add(QuestionEnum.AC);
        categoryList.add(QuestionEnum.ER);
        categoryList.add(QuestionEnum.GD);
        categoryList.add(QuestionEnum.ETC);
        categoryList.add(QuestionEnum.SH);
        categoryList.add(QuestionEnum.PR);
        return categoryList;
    }

    /**
     * 1:1 문의 한개 조회
     */
    public Question findOne(Long id) {
        Optional<Question> byId = questionRepository.findById(id);
        if (!byId.isPresent()) {
            throw new DMException("존재하지 않는 문의글입니다.");
        }
        List<QuestionComment> commentList = questionCommentService.findAllByQuestionId(id);
        byId.get().setQuestionCommentList(commentList);
        return byId.get();
    }

    /**
     * 1:1 문의 한개 DTO로 조회
     */
    public QuestionDto findOneByDto(Long id) {
        Question question = findOne(id);
        return questionToDto(question);
    }

    /**
     * 문의글 엔티티 -> DTO로 변환
     */
    private QuestionDto questionToDto(Question question) {
        QuestionDto dto = new QuestionDto();
        if (question.getId() != null) {
            dto.setId(question.getId());
        }
        if (question.getTitle() != null) {
            dto.setTitle(question.getTitle());
        }
        if (question.getContents() != null) {
            dto.setContents(question.getContents());
        }
        if (question.getCategoryId() != null) {
            dto.setCategoryId(question.getCategoryId());
        }
        if (question.getImgPath1() != null) {
            dto.setImgPath1(question.getImgPath1());
        }
        if (question.getImgName1() != null) {
            dto.setImgName1(question.getImgName1());
        }
        if (question.getImgPath2() != null) {
            dto.setImgPath2(question.getImgPath2());
        }
        if (question.getImgName2() != null) {
            dto.setImgName2(question.getImgName2());
        }
        if (question.getImgPath3() != null) {
            dto.setImgPath3(question.getImgPath3());
        }
        if (question.getImgName3() != null) {
            dto.setImgName3(question.getImgName3());
        }
        if (question.getCreateId() != null) {
            dto.setCreateId(question.getCreateId());
        }
        if (question.getCreateDate() != null) {
            dto.setCreateDate(question.getCreateDate());
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
                if (comment.getCreateDate() != null) {
                    commentDto.setCreateDate(comment.getCreateDate());
                }
                if (comment.getCreateId() != null) {
                    commentDto.setCreateId(comment.getCreateId());
                }
                commentDtoList.add(commentDto);
            }
            dto.setCommentList(commentDtoList);
        }
        return dto;
    }

    /**
     * 전체 문의글 갯수 조회
     */
    public int selectTotalCount() {
        return questionRepository.findAll().size();
    }

    /**
     * 사용자 문의글 목록
     * id = 회원 id
     */
    public Map<String, Object> findAllByUser(String createId, int page) {
        List<Question> questionList = questionRepository.findQuestionListByCreateId(createId, PageRequest.of(page, 10, Sort.by("id").descending())).getContent();

        List<QuestionDto> dtoList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();

        for (Question question : questionList) {
            QuestionDto dto = questionToDto(question);
            dtoList.add(dto);
            int commentCount = qcRepository.commentCount(dto.getId());
            countList.add(commentCount);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("questionList", dtoList);
        result.put("countList", countList);
        return result;
    }

    /**
     * 1:1 문의 등록
     */
    @Transactional
    public Long save(QuestionDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Question question = new Question();
        if (dto.getTitle() != null) {
            question.setTitle(dto.getTitle());
        } else {
            throw new DMException("제목을 입력해주세요.");
        }
        if (dto.getContents() != null) {
            question.setContents(dto.getContents());
        } else {
            throw new DMException("내용을 입력해주세요.");
        }
        if (dto.getCategoryId() != null) {
            question.setCategoryId(dto.getCategoryId());
        } else {
            throw new DMException("카테고리를 입력해주세요.");
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
        question.setCreateId(loginId);
        question.setUpdateId(loginId);

        questionRepository.save(question);
        return question.getId();
    }

    /**
     * 1:1 문의 수정
     */
    @Transactional
    public void update(Long id, QuestionDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();

        Question question = findOne(id);
        if (dto.getTitle() != null) {
            question.setTitle(dto.getTitle());
        }
        if (dto.getContents() != null) {
            question.setContents(dto.getContents());
        }
        if (dto.getCategoryId() != null) {
            question.setCategoryId(dto.getCategoryId());
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
        question.setUpdateId(loginId);
    }

    /**
     * 1:1 문의 삭제
     */
    @Transactional
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    /**
     * 문의글 검색(관리자)
     */
    @Transactional
    public List<QuestionDto> search(QuestionEnum categoryId, String keyword, String type, int page) {
        List<Question> result = new ArrayList<>();
        if (type.equals("T")) {
            result = questionRepository.findAllByCategoryIdAndTitleContaining(
                    categoryId, keyword, PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
        } else if (type.equals("I")) {
            result = questionRepository.findAllByCategoryIdAndCreateIdContaining(
                    categoryId, keyword, PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
        }

        List<QuestionDto> dtoList = new ArrayList<>();
        for (Question question : result) {
            QuestionDto dto = questionToDto(question);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
