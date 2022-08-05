package com.example.chatscoop.model;

import lombok.Data;

@Data
public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;

    public ChatNotification(String id, String senderId, String senderName) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
    }
}
