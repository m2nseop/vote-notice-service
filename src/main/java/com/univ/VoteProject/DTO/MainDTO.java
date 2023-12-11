package com.univ.VoteProject.DTO;

import com.univ.VoteProject.Model.Announce;
import com.univ.VoteProject.Model.Vote;
import java.util.List;

public class MainDTO {
    private List<Vote> votes;
    private List<Announce> announces;

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Announce> getAnnounces() {
        return announces;
    }

    public void setAnnounces(List<Announce> announces) {
        this.announces = announces;
    }
}
