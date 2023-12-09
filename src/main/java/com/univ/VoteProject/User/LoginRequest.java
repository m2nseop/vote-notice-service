package com.univ.VoteProject.User;

public class LoginRequest {
    private String id;
    private String password;
    private int loginAttempts; // 계정별 로그인 시도 횟수
    private String accountId; // 계정 아이디

    // Getter 및 Setter 메서드

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
