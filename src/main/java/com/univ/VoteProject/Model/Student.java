package com.univ.VoteProject.Model;


import lombok.Builder;
import lombok.Data;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

public class Student {
    private String id;
    private String password;
    private String name;
    private String email;
    private int status;
    private int position;
    private String college_name;
    private String dept_name;

//    public void encodingPassword(PasswordEncoder passwordEncoder) {
//        if (StringUtils.isEmpty(password)) {
//            return;
//        }
//        password = passwordEncoder.encode(password);
//    }
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getStatus() {
        return status;
    }

    public int getPosition() {
        return position;
    }

    public String getCollegeName() {
        return college_name;
    }

    public String getDeptName() {
        return dept_name;
    }
}
