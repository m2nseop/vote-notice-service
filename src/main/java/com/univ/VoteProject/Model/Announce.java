package com.univ.VoteProject.Model;

import java.time.LocalDateTime;

public class Announce {

    private int ann_id;

    private String id;
    private String name;
    private String ann_title;

    private String ann_content;

    private LocalDateTime ann_period;

    private int media_id;

    private String target_scope;

    public String getAnnContent() {
        return ann_content;
    }

    public void setAnnContent(String annContent) {
        this.ann_content = annContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 게터와 세터 메소드들
    public int getAnnId() {
        return this.ann_id;
    }

    public void setAnnId(int annId) {
        this.ann_id = annId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnTitle() {
        return this.ann_title;
    }

    public void setAnnTitle(String annTitle) {
        this.ann_title = annTitle;
    }

    public LocalDateTime getAnnPeriod() {
        return ann_period;
    }

    public void setAnnPeriod(LocalDateTime annPeriod) {
        this.ann_period = annPeriod;
    }

    public int getMediaId() {
        return media_id;
    }

    public void setMediaId(int mediaId) {
        this.media_id = mediaId;
    }

    public String getTargetScope() {
        return target_scope;
    }

    public void setTargetScope(String targetScope) {
        this.target_scope = targetScope;
    }
}

