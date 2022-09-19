package com.example.chatscoop.model;

import java.util.Date;

import lombok.Data;

@Data
public class Notification {
    private String id;
    private String senderId;
    private String recipientId;
    private NotificationType type;
    private Date createdAt;

    public Notification(String id, String senderId, String recipientId, NotificationType type) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.type = type;
        this.createdAt = new Date();
    }

    public enum NotificationType {
        SENT_RECEIPT,
        DELIVERED_RECEIPT,
        READ_RECEIPT,
        ERROR_RECEIPT,
        CHAT,
    }
}
