package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Model.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnounceService {
    @Autowired
    public AnnounceRepository announceRepo;

    public void createAnnounceAgenda(Announce announce){
        announceRepo.createAnnounceAgenda(announce);
    }
}
