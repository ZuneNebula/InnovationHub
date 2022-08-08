package com.stackroute.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Notification {
    @Id
    private String notificationId;
    private String sourceType;
    private String sourceId;
    private String targetType;
    private String targetId;
    private String status;
    private String innovationId;
    private String proposalId;
    private Date date;
    private NotificationData notificationData;

    public Notification() {
    }

    public Notification(String notificationId, String sourceType, String sourceId, String targetType, String targetId, String status, String innovationId, String proposalId, Date date, NotificationData notificationData) {
        this.notificationId = notificationId;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.status = status;
        this.innovationId = innovationId;
        this.proposalId = proposalId;
        this.date = date;
        this.notificationData = notificationData;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInnovationId() {
        return innovationId;
    }

    public void setInnovationId(String innovationId) {
        this.innovationId = innovationId;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NotificationData getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(NotificationData notificationData) {
        this.notificationData = notificationData;
    }
}
