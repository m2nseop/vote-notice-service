package com.univ.VoteProject.Vote;

import java.util.UUID;

public class TokenGenerator {
    public static String generateToken() {
        // UUID를 사용하여 랜덤한 토큰 생성
        return UUID.randomUUID().toString();
    }
}