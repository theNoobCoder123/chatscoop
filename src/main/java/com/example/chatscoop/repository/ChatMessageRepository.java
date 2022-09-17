package com.example.chatscoop.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.chatscoop.model.ChatMessage;
// import com.example.chatscoop.model.ChatMessage.MessageStatus;
// import com.example.chatscoop.model.ChatMessage.MessageType;

@Repository
public interface ChatMessageRepository extends CassandraRepository<ChatMessage, String> {
  // List<ChatMessage> findBySenderId(String senderId);
  // List<ChatMessage> findBySenderName(String senderName);

  // List<ChatMessage> findByRecipientId(String recipientId);
  // List<ChatMessage> findByRecipientName(String recipientName);
  
  // List<ChatMessage> findByType(MessageType type);
  // List<ChatMessage> findByStatus(MessageStatus Status);
}