package com.example.chatscoop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChatNotification extends Notification {
    private String senderId;
    private String senderName;

    public ChatNotification(String id, String senderId, String recipientId, String senderName) {
        super(id, senderId, recipientId, NotificationType.CHAT);
        this.senderId = senderId;
        this.senderName = senderName;
    }
}
