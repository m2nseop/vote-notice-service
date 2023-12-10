package com.univ.VoteProject.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Announce {

    private int annId;

    private String id;

    private String annTitle;

    private String annContent;

    private LocalDateTime annPeriod;

    private int mediaId;

    private String targetScope;

    public String getAnnContent() {
        return annContent;
    }

    public void setAnnContent(String annContent) {
        this.annContent = annContent;
    }

    // 게터와 세터 메소드들
    public int getAnnId() {
        return annId;
    }

    public void setAnnId(int annId) {
        this.annId = annId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnTitle() {
        return annTitle;
    }

    public void setAnnTitle(String annTitle) {
        this.annTitle = annTitle;
    }

    public LocalDateTime getAnnPeriod() {
        return annPeriod;
    }

    public void setAnnPeriod(LocalDateTime annPeriod) {
        this.annPeriod = annPeriod;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getTargetScope() {
        return targetScope;
    }

    public void setTargetScope(String targetScope) {
        this.targetScope = targetScope;
    }
}

