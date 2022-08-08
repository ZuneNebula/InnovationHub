package com.niit.proposal.model;

import java.util.Date;

public class PrivateComments {
    private String commentator;
    private String content;
    private Date commentDate;
    private String avatarUrl;

    public PrivateComments() {
    }

    public PrivateComments(String commentator, String content, Date commentDate, String avatarUrl) {
        this.commentator = commentator;
        this.content = content;
        this.commentDate = commentDate;
        this.avatarUrl = avatarUrl;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
