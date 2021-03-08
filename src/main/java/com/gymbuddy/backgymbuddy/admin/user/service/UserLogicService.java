package com.gymbuddy.backgymbuddy.admin.user.service;

import com.gymbuddy.backgymbuddy.admin.base.Address;
import com.gymbuddy.backgymbuddy.admin.exception.DMException;
import com.gymbuddy.backgymbuddy.admin.user.domain.Auth;
import com.gymbuddy.backgymbuddy.admin.user.domain.Grade;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import com.gymbuddy.backgymbuddy.admin.user.domain.UserDto;
import com.gymbuddy.backgymbuddy.admin.user.repository.AuthRepository;
import com.gymbuddy.backgymbuddy.admin.user.repository.UserRepository;
import com.gymbuddy.backgymbuddy.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserLogicService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 인증번호 저장
     * @param email 이메일
     * @param authNum 인증번호
     */
    @Transactional
    public void saveAuthNum(String email, String authNum) {
        Auth auth = new Auth();
        auth.setEmail(email);
        auth.setAuthNum(authNum);
        authRepository.save(auth);
    }

    /**
     * 이메일로 회원 엔티티 조회
     */
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * 아이디 찾기
     */
    public String findIdentityByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (!byEmail.isPresent()) {
            throw new DMException("존재하지 않는 이메일입니다.");
        }
        return byEmail.get().getIdentity();
    }

    /**
     * 이메일로 인증 엔티티 조회
     */
    public Auth findOneAuthByEmail(String email) {
        List<Auth> authList = authRepository.findByEmailOrderByIdDesc(email);
        return authList.get(0);
    }

    /**
     * 아이디로 인증 엔티티 조회
     */
    public Auth findOneAuthById(Long id) {
        return authRepository.findById(id).get();
    }

    /**
     * 회원가입
     */
    @Transactional
    public Map<String, Object> join(UserDto user) {
        Map<String, Object> result = new HashMap<>();
        // 이이디 중복 확인
        Optional<User> byIdentity = userRepository.findByIdentity(user.getIdentity());
        if (byIdentity.isPresent()) {
            result.put("successYn", "N");
            result.put("msg", "이미 사용중인 아이디입니다.");
            return result;
        }
        // 이메일 중복 확인
        if (checkDuplicateEmail(user.getEmail())) {
            result.put("successYn", "N");
            result.put("msg", "이미 가입된 이메일입니다.");
            return result;
        }

        User entity = new User();
        if (user.getIdentity() != null) {
            entity.setIdentity(user.getIdentity());
        }
        if (user.getEmail() != null) {
            entity.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getName() != null) {
            entity.setName(user.getName());
        }
        if (user.getPhone() != null) {
            entity.setPhone(user.getPhone());
        }
        if (user.getStreet1() != null && user.getStreet2() != null && user.getZipcode() != null) {
            entity.setAddress(new Address(user.getZipcode(), user.getStreet1(), user.getStreet2()));
        }
        if (user.getAgreeYn() != null) {
            entity.setAgreeYn(user.getAgreeYn());
        }
        entity.setGrade(Grade.NORMAL.toString());
        entity.setRoles(Collections.emptyList());

        userRepository.save(entity);

        result.put("successYn", "Y");
        result.put("id", entity.getId());
        return result;
    }

    /**
     * 이메일 중복 확인
     */
    public boolean checkDuplicateEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        // 이메일 중복 확인
        if (byEmail.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * 로그인
     */
    public Map<String, Object> login(UserDto user) {
        Map<String, Object> result = new HashMap<>();
        Optional<User> tmpMember = userRepository.findByIdentity(user.getIdentity());
        if (!tmpMember.isPresent()) {
            result.put("successYn", "N");
            result.put("msg", "가입되지 않은 이메일입니다.");
            return result;
        }
        User member = tmpMember.get();
        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            result.put("successYn", "N");
            result.put("msg", "잘못된 비밀번호 입니다.");
            return result;
        }

        result.put("id", member.getId());
        result.put("identity", member.getIdentity());
        result.put("name", member.getName());
        result.put("adminYn", "ADMIN".equals(member.getGrade()) ? "Y" : "N");
        result.put("jwt-token", jwtTokenProvider.createToken(member.getUsername()));
        return result;
    }

    /**
     * 전체 회원수 조회
     */
    public int selectTotalCount() {
        return userRepository.findAll().size();
    }

    /**
     * 전체 회원 조회
     */
    public List<UserDto> findAll(int page, String grade) {
        List<User> entities;
        if ("all".equals(grade)) {
            entities = userRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
        } else {
            entities = userRepository.findAllByGrade(grade.toUpperCase(), PageRequest.of(page, 10, Sort.by("id").descending()));
        }

        List<UserDto> list = new ArrayList<>();
        for (User user : entities) {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setGrade(user.getGrade());
            dto.setIdentity(user.getIdentity());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setAgreeYn(user.getAgreeYn());
            list.add(dto);
        }
        return list;
    }

    /**
     * 회원정보 조회(엔티티)
     */
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }

    /**
     * 회원정보 조회(dto)
     */
    public UserDto findUserDto(Long id) {
        User user = findOne(id);

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setIdentity(user.getIdentity());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        Address address = user.getAddress();
        dto.setZipcode(address.getZipcode());
        dto.setStreet1(address.getStreet1());
        dto.setStreet2(address.getStreet2());
        dto.setAgreeYn(user.getAgreeYn());
        dto.setGrade(user.getGrade());

        return dto;
    }

    /**
     * 회원등급 수정
     */
    @Transactional
    public void updateGrade(Map<String, Object> param) {
        String grade = Objects.toString(param.get("grade")).toUpperCase();
        List<Integer> idList = (List<Integer>) param.get("idList");
        for (int id : idList) {
            long idL = new Long(id);
            User user = userRepository.findById(idL).get();
            user.setGrade(grade);
        }
    }

    /**
     * 정보수정
     */
    @Transactional
    public void update(Long id, UserDto user) {
        User origin = findOne(id);
        if (origin.getName() != null) {
            origin.setName(user.getName());
        }
        if (user.getPassword() != null) {
            String newPw = passwordEncoder.encode(user.getPassword());
            origin.setPassword(newPw);
        }
        if (origin.getPhone() != null) {
            origin.setPhone(user.getPhone());
        }
        if (user.getStreet1() != null && user.getStreet2() != null && user.getZipcode() != null) {
            Address address = new Address(user.getZipcode(), user.getStreet1(), user.getStreet2());
            if (!origin.getAddress().equals(address)) {
                origin.setAddress(address);
            }
        }
        if (origin.getAgreeYn() != null) {
            origin.setAgreeYn(user.getAgreeYn());
        }
    }

    /**
     * 탈퇴
     */
    @Transactional
    public void signOut(Long id) {
        User origin = findOne(id);
        userRepository.delete(origin);
    }

    /**
     * 비밀번호 변경
     * @param user 회원정보
     * @param authNum 임시비밀번호
     */
    @Transactional
    public void updatePassword(User user, String authNum) {
        User origin = userRepository.findByEmail(user.getEmail()).get();
        String password = passwordEncoder.encode(authNum);
        origin.setPassword(password);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 비밀번호 재확인
     */
    public Map<String, Object> checkPwAgain(UserDto user) {
        Map<String, Object> result = new HashMap<>();
        Optional<User> origin = userRepository.findByIdentity(user.getIdentity());
        if (origin.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), origin.get().getPassword())) {
                result.put("successYn", "Y");
                result.put("msg", "비밀번호가 일치합니다.");
                return result;
            }
        }
        result.put("successYn", "N");
        result.put("msg", "비밀번호가 일치하지 않습니다.");
        return result;
    }

}
