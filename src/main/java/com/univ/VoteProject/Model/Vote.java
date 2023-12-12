package com.univ.VoteProject.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {
    private int vote_id;
    private String id;
    private String title;
    private String content;
    private int media_id;
    private LocalDateTime vote_period;
    private String target_scope;
    private String attendance_type;
    private String anonymity_type;
    private String qr_token;

    // Getter와 Setter 메서드

    public int getVoteId() {
        return vote_id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getMediaId() {
        return media_id;
    }

    public LocalDateTime getVotePeriod() {
        return vote_period;
    }

    public String getTargetScope() {
        return target_scope;
    }

    public String getAttendanceType() {
        return attendance_type;
    }

    public String getAnonymityType() {
        return anonymity_type;
    }

    public String getQrToken() {
        return qr_token;
    }

    public void setVoteId(int voteId) {
        this.vote_id = voteId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediaId(int mediaId) {
        this.media_id = mediaId;
    }

    public void setVotePeriod(LocalDateTime votePeriod) {
        this.vote_period = votePeriod;
    }

    public void setTargetScope(String targetScope) {
        this.target_scope = targetScope;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendance_type = attendanceType;
    }

    public void setAnonymityType(String anonymityType) {
        this.anonymity_type = anonymityType;
    }

    public void setQrToken(String qrId) {
        this.qr_token = qrId;
    }
}
