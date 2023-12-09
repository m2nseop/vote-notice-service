package com.univ.VoteProject.User;

import com.univ.VoteProject.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository repo;

//    public Student getStudentAccount(final String id) {
//        return mapper.getStudentAccount(id);
//    }

    public Student findUserByLoginId(final String id, final String password) {
        return repo.findUserByLoginId(id, password);
    }

    public Student login(final String id, final String password) {

        // 1. 회원 정보 및 비밀번호 조회
        Student student = findUserByLoginId(id, password);
//        String encodedPassword = (student == null) ? "" : student.getPassword();
//
//        // 2. 회원 정보 및 비밀번호 체크
//        if (student == null || passwordEncoder.matches(password, encodedPassword) == false) {
//            return null;
//        }
//
//        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
//        student.clearPassword();
        return student;
    }
}
