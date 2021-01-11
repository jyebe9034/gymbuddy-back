package com.gymbuddy.backgymbuddy.admin.user.controller;

import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import com.gymbuddy.backgymbuddy.admin.user.repository.UserRepository;
import com.gymbuddy.backgymbuddy.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

//    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, Object> user) {
        return userRepository.save(User.builder()
                .email((String) user.get("email"))
                .password(passwordEncoder.encode((String) user.get("password")))
//                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입 시 USER로 설정
                .roles(Collections.emptyList())
                .build()).getId();
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> user, HttpServletResponse response) {
        User member = userRepository.findByEmail((String) user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));
        if (!passwordEncoder.matches((String) user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        response.addHeader("jwt-token", jwtTokenProvider.createToken(member.getUsername(), member.getRoles()));
        return response.toString();
    }

    /**
     * 정보 수정
     */
    @PostMapping("/editUser")
    public Map<String, Object> editUser(@RequestBody Map<String, Object> user) {
        // 내 정보 수정
        return null;
    }

    /**
     * 탈퇴
     */
    @PostMapping("/signOut")
    public Map<String, Object> signOut(@RequestBody Map<String, Object> user) {
        // 탈퇴
        return null;
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public Map<String, Object> logout(@RequestParam Map<String, Object> user) {
        // 로그아웃
        return null;
    }
}
