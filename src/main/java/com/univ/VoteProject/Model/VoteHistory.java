package com.univ.VoteProject.Model;

import java.time.LocalDateTime;

public class VoteHistory {
    private int vote_id;
    private String id;
    private int decision;
    private LocalDateTime vote_date;

    public int getVoteId() {
        return vote_id;
    }

    public void setVoteId(int vote_id) {
        this.vote_id = vote_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public LocalDateTime getVoteDate() {
        return vote_date;
    }

    public void setVoteDate(LocalDateTime vote_date) {
        this.vote_date = vote_date;
    }
}
