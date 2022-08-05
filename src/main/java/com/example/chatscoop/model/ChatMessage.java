package com.example.chatscoop.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChatMessage {
    private String id;
    private String chatId;
    private String senderId;
    private String senderName;
    private String recipientId;
    private String recipientName;
    private String content;
    private MessageType type;
    private MessageStatus status;
    private Date timestamp;

    public enum MessageType {
        CHAT,
        REPLY,
        JOIN,
        LEAVE
    }

    public enum MessageStatus {
        SENT,
        DELIVERED,
        READ,
        PENDING,
        ERROR
    }
}
