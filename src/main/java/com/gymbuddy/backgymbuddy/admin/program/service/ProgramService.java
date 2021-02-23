package com.gymbuddy.backgymbuddy.admin.program.service;

import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramDto;
import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOption;
import com.gymbuddy.backgymbuddy.admin.program.domain.ProgramOptionDto;
import com.gymbuddy.backgymbuddy.admin.program.repository.ProgramOptionRepository;
import com.gymbuddy.backgymbuddy.admin.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramOptionRepository optionRepository;

    public List<Program> findAll(int page) {
        return programRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public List<ProgramDto> findAllDto(int page) {
        List<Program> list = findAll(page);
        List<ProgramDto> dtoList = new ArrayList<>();
        for (Program one : list) {
            ProgramDto dto = entityToDto(one);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Program findOne(Long id) {
        Program program = programRepository.findById(id).get();
        List<ProgramOption> options = optionRepository.findAllByProgramId(id);
        program.setProgramOptions(options);
        return program;
    }

    public ProgramDto findOneDto(Long id) {
        Program one = findOne(id);
        return entityToDto(one);
    }

    private ProgramDto entityToDto(Program one) {
        ProgramDto dto = new ProgramDto();
        if (one.getId() != null) {
            dto.setId(one.getId());
        }
        if (one.getTitle() != null) {
            dto.setTitle(one.getTitle());
        }
        if (one.getCoach() != null) {
            dto.setCoach(one.getCoach());
        }
        if (one.getClassAddress() != null) {
            dto.setClassAddress(one.getClassAddress());
        }
        if (one.getClassDate() != null) {
            dto.setClassDate(one.getClassDate());
        }
        if (one.getClassTime() != null) {
            dto.setClassTime(one.getClassTime());
        }
        if (one.getPrice() != null) {
            dto.setPrice(one.getPrice());
        }
        if (one.getMainYn() != null) {
            dto.setMainYn(one.getMainYn());
        }
        if (one.getThumbnailImgPath() != null) {
            dto.setThumbnailImgPath(one.getThumbnailImgPath());
        }
        if (one.getThumbnailImgName() != null) {
            dto.setThumbnailImgName(one.getThumbnailImgName());
        }
        if (one.getDetailImgPath() != null) {
            dto.setDetailImgPath(one.getDetailImgPath());
        }
        if (one.getDetailImgName() != null) {
            dto.setDetailImgName(one.getDetailImgName());
        }
        if (!one.getProgramOptions().isEmpty()) {
            List<ProgramOptionDto> optionList = new ArrayList<>();
            for (ProgramOption opt : one.getProgramOptions()) {
                ProgramOptionDto optDto = new ProgramOptionDto();
                if (opt.getId() != null) {
                    optDto.setId(opt.getId());
                }
                if (opt.getClassDateTime() != null) {
                    optDto.setClassDateTime(opt.getClassDateTime());
                }
                if (opt.getUserCount() != 0) {
                    optDto.setUserCount(opt.getUserCount());
                }
                if (opt.getAddPrice() != null) {
                    optDto.setAddPrice(opt.getAddPrice());
                }
                optionList.add(optDto);
            }
            dto.setOptionList(optionList);
        }
        return dto;
    }

    public ProgramOption findOneOption(Long id) {
        return optionRepository.findById(id).get();
    }

    @Transactional
    public Long save(ProgramDto program) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Program entity = new Program();
        if (program.getTitle() != null) {
            entity.setTitle(program.getTitle());
        }
        if (program.getCoach() != null) {
            entity.setCoach(program.getCoach());
        }
        if (program.getClassAddress() != null) {
            entity.setClassAddress(program.getClassAddress());
        }
        if (program.getClassDate() != null) {
            entity.setClassDate(program.getClassDate());
        }
        if (program.getClassTime() != null) {
            entity.setClassTime(program.getClassTime());
        }
        if (program.getPrice() != null) {
            entity.setPrice(program.getPrice());
        }
        if (program.getMainYn() != null) {
            entity.setMainYn(program.getMainYn());
        }
        if (program.getThumbnailImgName() != null) {
//        entity.setThumbnailImgName(program.getThumbnailImgName());
        }
        if (program.getThumbnailImgPath() != null) {
//        entity.setThumbnailImgPath(program.getThumbnailImgPath());
        }
        if (program.getDetailImgName() != null) {
//        entity.setDetailImgName(program.getDetailImgName());
        }
        if (program.getDetailImgPath() != null) {
//        entity.setDetailImgPath(program.getDetailImgPath());
        }
        entity.setCreateDate(LocalDateTime.now());
//        entity.setCreateId(loginId);
        entity.setUpdateDate(LocalDateTime.now());
//        entity.setUpdateId(loginId);

        programRepository.save(entity);

        // 옵션 저장
        List<ProgramOptionDto> optionList = program.getOptionList();
        for (ProgramOptionDto dto : optionList) {
            ProgramOption option = new ProgramOption();
            if (dto.getClassDateTime() != null) {
                option.setClassDateTime(dto.getClassDateTime());
            }
            if (dto.getUserCount() != 0) {
                option.setUserCount(dto.getUserCount());
            }
            if (dto.getAddPrice() != null) {
                option.setAddPrice(dto.getAddPrice());
            }
            option.setProgram(entity);
            option.setCreateDate(LocalDateTime.now());
//        option.setCreateId(loginId);
            option.setUpdateDate(LocalDateTime.now());
//        option.setUpdateId(loginId);
            optionRepository.save(option);
        }

        return entity.getId();
    }

    @Transactional
    public void update(Long id, ProgramDto program) {
        // 현재 로그인한 아이디 정보 조회
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails) principal;
//        String loginId = userDetails.getUsername();

        Program origin = findOne(id);
        if (!origin.getTitle().equals(program.getTitle())) {
            origin.setTitle(program.getTitle());
        }
        if (!origin.getCoach().equals(program.getCoach())) {
            origin.setCoach(program.getCoach());
        }
        if (!origin.getClassAddress().equals(program.getClassAddress())) {
            origin.setClassAddress(program.getClassAddress());
        }
        if (!origin.getClassDate().equals(program.getClassDate())) {
            origin.setClassDate(program.getClassDate());
        }
        if (!origin.getClassTime().equals(program.getClassTime())) {
            origin.setClassTime(program.getClassTime());
        }
        if (!origin.getPrice().equals(program.getPrice())) {
            origin.setPrice(program.getPrice());
        }
        if (!origin.getMainYn().equals(program.getMainYn())) {
            origin.setMainYn(program.getMainYn());
        }
        if (origin.getThumbnailImgPath() != null && !origin.getThumbnailImgPath().equals(program.getThumbnailImgPath())) {
            origin.setThumbnailImgPath(program.getThumbnailImgPath());
        }
        if (origin.getThumbnailImgName() != null && !origin.getThumbnailImgName().equals(program.getThumbnailImgName())) {
            origin.setThumbnailImgName(program.getThumbnailImgName());
        }
        if (origin.getDetailImgPath() != null && !origin.getDetailImgPath().equals(program.getDetailImgPath())) {
            origin.setDetailImgPath(program.getDetailImgPath());
        }
        if (origin.getDetailImgName() != null && !origin.getDetailImgName().equals(program.getDetailImgName())) {
            origin.setDetailImgName(program.getDetailImgName());
        }
        origin.setUpdateDate(LocalDateTime.now());
//        origin.setUpdateId(loginId);

        // 옵션 수정..
        List<ProgramOptionDto> optionList = program.getOptionList();
        if (!optionList.isEmpty()) {
            for (ProgramOptionDto dto : optionList) {
                ProgramOption originOption = findOneOption(dto.getId());
                if (!originOption.getClassDateTime().equals(dto.getClassDateTime())) {
                    originOption.setClassDateTime(dto.getClassDateTime());
                }
                if (originOption.getUserCount() != dto.getUserCount()) {
                    originOption.setUserCount(dto.getUserCount());
                }
                if (!originOption.getAddPrice().equals(dto.getAddPrice())) {
                    originOption.setAddPrice(dto.getAddPrice());
                }
            }
        }
    }

    @Transactional
    public void delete(long id) {
        programRepository.deleteById(id);
        // 옵션 목록 삭제..
        optionRepository.deleteByProgramId(id);
    }
}
