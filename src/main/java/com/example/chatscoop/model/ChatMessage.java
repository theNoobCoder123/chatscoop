package com.example.chatscoop.model;

import java.util.Date;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "messages")
public class ChatMessage {

    @PrimaryKeyColumn(name = "message_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String id;

    @PrimaryKeyColumn(name = "sender_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String senderId;
    private String senderName;

    @PrimaryKeyColumn(name = "recipient_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
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
        LEAVE,
    }

    public enum MessageStatus {
        PENDING,
        SENT,
        DELIVERED,
        READ,
        ERROR,
    }

    @Override
    public String toString() {
        return "Chat Message:-" +
                "\t" + "Id :" + this.id +
                "\t" + "Sender Id, Name :" + this.senderId + ", " + this.senderName +
                "\t" + "Recipient Id, Name :" + this.recipientId + ", " + this.recipientName +
                "\t" + "Content :" + this.content +
                "\t" + "Type :" + this.type +
                "\t" + "Status :" + this.status +
                "\t" + "Timestamp :" + this.timestamp;
    }
}
