package com.gymbuddy.backgymbuddy.admin.user.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.user.domain.Auth;
import com.gymbuddy.backgymbuddy.admin.user.domain.AuthDto;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import com.gymbuddy.backgymbuddy.admin.user.domain.UserDto;
import com.gymbuddy.backgymbuddy.admin.user.service.UserLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_USER_API;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.USER_API;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserLogicService logicService;
    private final PasswordEncoder passwordEncoder;

    // 메일발송을 위함 운동친구 메일 계정
    private static String HOSTMAIL = "jyebe9034@gmail.com";
    private static String HOSTPW = "7pGA9034!";

    /**
     * 인증번호 메일 발송
     */
    @PostMapping(USER_API + "/sendAuthNumber")
    public ResponseEntity<Map<String, Object>> sendAuthNumber(@RequestBody UserDto user) {
        log.info("인증번호 발송을 위한 메일번호: {}", user);
        Map<String, Object> result = new HashMap<>();

        // 이미 가입된 이메일인지 확인
        if (logicService.checkDuplicateEmail(user.getEmail())) {
            result.put("successYn", "N");
            result.put("msg", "이미 가입된 이메일입니다.");
            return createResponseEntity(true, result);
        }

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HOSTMAIL, HOSTPW);
            }
        });

        // 인증번호 생성
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String authNum = "";
        for (int i = 0; i < 6; i++) {
            int idx = (int) (charSet.length * Math.random());
            authNum += charSet[idx];
        }
        result.put("authNum", authNum);

        // 이메일 내용
        String content = "<div style='background-color: #00AD84; border:4px solid #231815; text-align: center;'>" +
                "<div><img src='gymbuddy.co.kr/resources/static/img/logo.png' alt='운동친구 로고' style='margin:60px 0 50px 0;' width='140px'></div>" +
                "<div style='font: 700 16pt sans-serif; line-height: 140%;'>" +
                "안녕하세요, " + user.getName() + " 님!<br>" + "회원님의 인증번호는" + authNum + "입니다.</div>";

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(HOSTMAIL));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

            // 메일 제목
            message.setSubject("[운동친구] 본인인증을 위한 인증 메일입니다.");
            // 메일 내용
            message.setText(content, "UTF-8", "html");
            // 메일 전송
            Transport.send(message);

            // 이메일과 인증번호 DB에 저장
            logicService.saveAuthNum(user.getEmail(), authNum);
            Auth oneAuth = logicService.findOneAuthByEmail(user.getEmail());
            result.put("authId", oneAuth.getId());
            result.put("successYn", "Y");
        } catch (AddressException e) {
            log.error(e.getMessage());
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }

        return createResponseEntity(true, result);
    }

    /**
     * 인증번호 일치 확인
     */
    @PostMapping(USER_API + "/checkAuthNum")
    public ResponseEntity<Map<String, Object>> checkAuthNum(@RequestBody AuthDto auth) {
        log.info("인증번호 일치 확인: {}", auth);
        Auth origin = logicService.findOneAuthById(auth.getId());


        Map<String, Object> result = new HashMap<>();
        // 인증번호 비교
        if (origin.getAuthNum().equals(auth.getAuthNum())) {
            result.put("result", "Y");
        } else {
            result.put("result", "N");
        }
        return createResponseEntity(true, result);
    }

    /**
     * 임시 비밀번호 메일 발송
     */
    @PostMapping(USER_API + "/onetimePw")
    public ResponseEntity<Map<String, Object>> sendOnetimePassword(@RequestBody UserDto user) {
        log.info("임시 비밀번호 발송을 위한 정보: {}", user);
        Map<String, Object> result = new HashMap<>();

        // 가입 여부 확인
        if (!logicService.checkDuplicateEmail(user.getEmail())) {
            result.put("successYn", "N");
            result.put("msg", "가입된 회원정보가 없습니다. \n 다시 시도해주세요");
            return createResponseEntity(true, result);
        }

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HOSTMAIL, HOSTPW);
            }
        });

        // 인증번호 생성
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String authNum = "";
        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            authNum += charSet[idx];
        }

        // 이메일 내용
        String content = "<div style='background-color: #00AD84; border:4px solid #231815; text-align: center;'>" +
                "<div><img src='gymbuddy.co.kr/resources/static/img/logo.png' alt='운동친구 로고' style='margin:60px 0 50px 0;' width='140px'></div>" +
                "<div style='font: 700 16pt sans-serif; line-height: 140%;'>" +
                "안녕하세요, " + user.getName() + " 님!<br>" + "임시 비밀번호가 발급되었습니다.</div>" +
                "임시로 발급해드린 비밀번호는 <span style='color: yellow;'>" + authNum + "<span>입니다.<br>" +
                "로그인 후 마이페이지에서 비밀번호를 변경해주세요." +
                "<a href='gymbuddy.co.kr" + "/join/login'>"+
                "<div style='text-decoration:none; width: 300px; padding:10px 0; background-color: white; border: 4px solid #231815; margin:50px auto 60px; font: 700 10pt sans-serif; color: #231815;'>인증하기</div></a></div>";

        result.put("onetimePw", authNum);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(HOSTMAIL));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

            // 메일 제목
            message.setSubject("[운동친구] 임시 비밀번호가 발급되었습니다.");
            // 메일 내용
            message.setText(content, "UTF-8", "html");
            // 메일 전송
            Transport.send(message);
            log.info("Success Message Send");

            // 사용자의 비밀번호 변경
            logicService.updatePassword(user, authNum);
            result.put("successYn", "Y");
        } catch (AddressException e) {
            log.error(e.getMessage());
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }

        return createResponseEntity(true, result);
    }

    /**
     * 회원가입
     */
    @PostMapping(USER_API + "/join")
    public ResponseEntity<Map<String, Object>> join(@RequestBody UserDto user) {
        log.info("회원가입: {}", user);
        return createResponseEntity(true, logicService.join(user));
    }

    /**
     * 로그인
     */
    @PostMapping(USER_API + "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto user) {
        log.info("회원 로그인: {}", user.getIdentity());
        return createResponseEntity(true, logicService.login(user));
    }


    /**
     * 전체 회원수 조회
     */
    @GetMapping(USER_API + "/totalCount")
    public  ResponseEntity<Map<String, Object>> selectUserTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", logicService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 전체 회원 정보 조회(관리자)
     */
    @GetMapping(USER_API + "/all/{page}/{grade}")
    public ResponseEntity<List<UserDto>> selectUserList(@PathVariable("page") int page, @PathVariable("grade") String grade) {
        log.info("전체 회원정보 조회 - page: {}, grade: {}", page, grade);
        return createResponseEntity(true, logicService.findAll(page, grade));
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
     * 회원등급 수정
     */
    @PutMapping(USER_API + "/updateGrade")
    public ResponseEntity<Map<String, Object>> updateGrade(@RequestBody Map<String, Object> param) {
        log.info("회원 등급 수정: {}", param);
        logicService.updateGrade(param);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
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
        if (user.getName() != null) {
            flag = user.getName().equals(findUser.getName()) ? true : false;
        }
        if (user.getPassword() != null) {
            String pw = passwordEncoder.encode(user.getPassword());
            flag = pw.equals(findUser.getPassword()) ? true : false;
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
     * 회원 탈퇴
     */
    @DeleteMapping(USER_API + "/delete/{id}")
    public ResponseEntity<Map<String, Object>> signOut(@PathVariable("id") Long id) {
        log.info("회원 탈퇴: {}", id);
        logicService.signOut(id);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}
