package com.univ.VoteProject.Vote;

import com.univ.VoteProject.Model.Vote;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    public VoteRepository voteRepo;

    public void createVoteAgenda(Vote vote){
        voteRepo.createVoteAgenda(vote);
    }

    public List<Vote> getVoteList() { return voteRepo.getVoteList();}
}
