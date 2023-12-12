package com.univ.VoteProject.Vote;

import com.univ.VoteProject.Model.Vote;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class VoteService {
    @Autowired
    public VoteRepository voteRepo;

    public void createVoteAgenda(Vote vote){
        voteRepo.createVoteAgenda(vote);
    }

    public List<Vote> getVoteList() { return voteRepo.getVoteList();}

    public Vote getVoteById(int voteId){
        return voteRepo.getVoteById(voteId);
    }

    public Vote getVoteByToken(String token){
        return voteRepo.getVoteByToken(token);
    }
    public void castVote(int voteId, String voterId, String voterName, int decision){
        voteRepo.castVote(voteId, voterId, voterName, decision);
    }

    public int checkVoteById(String voterId, int voteId){
        return voteRepo.checkVoteById(voterId, voteId);
    }

    // 대면일 경우 생성하는 토큰
    public String generateToken(int voteId) {
        // QR 코드 및 토큰 생성
        String token = TokenGenerator.generateToken();

        // 유효 기간 설정 (예: 1시간)
//        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
        System.out.println(token);
        // 투표 정보 데이터베이스에 저장
        voteRepo.updateQrToken(token, voteId);

        // 토큰을 URL에 포함시킨다고 가정
        return "/vote/detail/" + token;
    }

    public void deleteVote(int voteId){
        voteRepo.deleteVote(voteId);
    }
}
