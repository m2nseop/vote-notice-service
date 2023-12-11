package com.univ.VoteProject.Announce;

import com.univ.VoteProject.Model.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnnounceService {
    @Autowired
    public AnnounceRepository announceRepo;

    public void createAnnounceAgenda(Announce announce){
        announceRepo.createAnnounceAgenda(announce);
    }

    public List<Announce> getAnnounceList(){
        return announceRepo.getAnnounceList();
    }
}
