package com.gymbuddy.backgymbuddy.admin.user.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import com.gymbuddy.backgymbuddy.admin.user.domain.UserDto;
import com.gymbuddy.backgymbuddy.admin.user.service.UserLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.USER_API;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

//    private final UserService userService;
    private final UserLogicService logicService;

    /**
     * 회원가입
     */
    @PostMapping(USER_API + "/join")
    public Long join(@RequestBody UserDto user) {
        log.info("회원가입: {}", user);
        return logicService.join(user);
    }

    /**
     * 로그인
     */
    @PostMapping(USER_API + "/login")
    public String login(@RequestBody UserDto user) {
        log.info("회원 로그인: {}", user.getIdentity());
        return logicService.login(user);
    }

    /**
     * 전체 회원 정보 조회(관리자)
     */
    @GetMapping(USER_API + "/all/{page}")
    public ResponseEntity<List<UserDto>> selectUserList(@PathVariable("page") int page) {
        return createResponseEntity(true, logicService.findAll(page));
    }

    /**
     * 회원 상세 정보 조회
     */
    @GetMapping(USER_API + "/detail/{id}")
    public ResponseEntity<UserDto> selectUserDetail(@PathVariable("id") Long id) {
        log.info("회원 정보 조회: {}", id);
        return createResponseEntity(true, logicService.findUserDto(id));
    }

    /**
     * 정보 수정
     */
    @PutMapping(USER_API + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") Long id ,@RequestBody UserDto user) {
        log.info("회원 정보 수정 - id: {}, user: {}", id, user);
        logicService.update(id, user);
        User findUser = logicService.findOne(id);

        boolean flag = true;
        if (user.getEmail() != null) {
            flag = user.getEmail().equals(findUser.getEmail()) ? true : false;
        }
        if (user.getName() != null) {
            flag = user.getName().equals(findUser.getName()) ? true : false;
        }
        if (user.getPhone() != null) {
            flag = user.getPhone().equals(findUser.getPhone()) ? true : false;
        }
        if (user.getZipcode() != null) {
            flag = user.getZipcode().equals(findUser.getAddress().getZipcode()) ? true : false;
        }
        if (user.getStreet1() != null) {
            flag = user.getStreet1().equals(findUser.getAddress().getStreet1()) ? true : false;
        }
        if (user.getStreet2() != null) {
            flag = user.getStreet2().equals(findUser.getAddress().getStreet2()) ? true : false;
        }
        if (user.getAgreeYn() != null) {
            flag = user.getAgreeYn().equals(findUser.getAgreeYn()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 탈퇴
     */
    @DeleteMapping(USER_API + "/signOut")
    public ResponseEntity<Map<String, Object>> signOut(@RequestBody UserDto user) {
        log.info("회원 탈퇴: {}", user);
        logicService.signOut(user);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

    /**
     * 로그아웃
     */
    @GetMapping(USER_API + "/logout")
    public Map<String, Object> logout(@RequestParam Map<String, Object> user) {
        return null;
    }
}
