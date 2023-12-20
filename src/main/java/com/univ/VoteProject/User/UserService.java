package com.univ.VoteProject.User;

import com.univ.VoteProject.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository repo;

    public Student findUserByLoginId(final String id, final String password) {
        return repo.findUserByLoginId(id, password);
    }

    public Student login(final String id, final String password) {

        // 1. 회원 정보 및 비밀번호 조회
        Student student = findUserByLoginId(id, password);
        return student;
    }
}
