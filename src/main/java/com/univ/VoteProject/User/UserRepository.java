package com.univ.VoteProject.User;

import com.univ.VoteProject.Model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {
    Student getStudentAccount(String id, String password);

    Student findUserByLoginId(String id, String password);
}
