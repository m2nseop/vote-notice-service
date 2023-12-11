package com.univ.VoteProject.User;

import com.univ.VoteProject.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

//    private Map<String, LoginRequest> loginAttemptsMap = new HashMap<>();

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        // 1. 회원 정보 조회
        System.out.println(loginRequest.getId());
        System.out.println(loginRequest.getPassword());

        // 2. 로그인 성공
        Student member = userService.login(loginRequest.getId(), loginRequest.getPassword());
        System.out.println(member.getDeptName());
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            // 로그인 성공 시 로그인 시도 횟수 초기화
//            loginAttemptRepository.resetLoginAttempts(loginRequest.getId());

            return ResponseEntity.ok("/home.do");
        } else {
            // 로그인 실패 시 계정별 로그인 시도 횟수 증가
            loginRequest.setLoginAttempts(loginRequest.getLoginAttempts() + 1);

            // 특정 계정에 대한 로그인 시도 횟수가 5회 이상이면 로그인 시도를 막음
            if (loginRequest.getLoginAttempts() >= 5) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 시도 횟수 초과. 계정이 잠겼습니다.");
            }

            // 로그인 실패 시 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 아이디 또는 비밀번호입니다. 남은 시도 횟수: " + (5 - loginRequest.getLoginAttempts()));
        }
    }
}
