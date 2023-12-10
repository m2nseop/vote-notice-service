package com.univ.VoteProject.Model;

import java.time.LocalDateTime;

public class Vote {
    private int voteId;
    private String id;
    private String title;
    private String content;
    private int mediaId;
    private LocalDateTime votePeriod;
    private String targetScope;
    private String attendanceType;
    private String anonymityType;
    private int qrId;

    // Getter와 Setter 메서드

    public int getVoteId() {
        return voteId;
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
        return mediaId;
    }

    public LocalDateTime getVotePeriod() {
        return votePeriod;
    }

    public String getTargetScope() {
        return targetScope;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public String getAnonymityType() {
        return anonymityType;
    }

    public int getQrId() {
        return qrId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public void setVotePeriod(LocalDateTime votePeriod) {
        this.votePeriod = votePeriod;
    }

    public void setTargetScope(String targetScope) {
        this.targetScope = targetScope;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public void setAnonymityType(String anonymityType) {
        this.anonymityType = anonymityType;
    }

    public void setQrId(int qrId) {
        this.qrId = qrId;
    }
}
