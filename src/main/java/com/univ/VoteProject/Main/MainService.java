package com.univ.VoteProject.Main;

import com.univ.VoteProject.Announce.AnnounceService;
import com.univ.VoteProject.DTO.MainDTO;
import com.univ.VoteProject.Model.Announce;
import com.univ.VoteProject.Model.Vote;
import com.univ.VoteProject.Vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final VoteService voteService;
    private final AnnounceService announceService;

    @Autowired
    public MainService(VoteService voteService, AnnounceService announceService) {
        this.voteService = voteService;
        this.announceService = announceService;
    }

    public List<Vote> getVoteList() {
        return voteService.getVoteList();
    }
    public List<Announce> getAnnounceList(){
        return announceService.getAnnounceList();
    }
}