package com.univ.VoteProject.Vote;

import com.univ.VoteProject.Model.Vote;
import java.util.List;
import java.util.UUID;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VoteRepository {
    void createVoteAgenda(Vote vote);

    List<Vote> getVoteList();

    Vote getVoteById(int voteId);

    Vote getVoteByToken(String token);

    void castVote(int voteId, String voterId, String voterName,int decision);

    int checkVoteById(String voterId, int voteId);

    void updateQrToken(String token, int voteId);

    void deleteVote(int voteId);
}
