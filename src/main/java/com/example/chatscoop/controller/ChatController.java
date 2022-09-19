package com.example.chatscoop.controller;

import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.chatscoop.model.ChatMessage;
import com.example.chatscoop.model.ChatMessage.MessageStatus;
import com.example.chatscoop.model.Notification.NotificationType;
import com.example.chatscoop.repository.ChatMessageRepository;
import com.example.chatscoop.service.ChatMessageIdService;
import com.example.chatscoop.model.ChatNotification;
import com.example.chatscoop.model.Notification;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    @Autowired
    private ChatMessageIdService chatMessageIdService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setId(chatMessageIdService.getChatId()); // TODO: Change to using twitter's service.
        chatMessage.setSentAt(Date.from(Instant.now()));
        chatMessage.setStatus(MessageStatus.SENT);
        logger.info(chatMessage.toString());
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        logger.info(savedMessage.toString());

        messagingTemplate.convertAndSendToUser(
                savedMessage.getSenderId(),
                "/queue/messages",
                new Notification(
                        savedMessage.getId(),
                        savedMessage.getSenderId(),
                        savedMessage.getRecipientId(),
                        NotificationType.SENT_RECEIPT));

        boolean recipientOnline = true; // TODO: Update this variable to use real session status.
        if (recipientOnline) {
            messagingTemplate.convertAndSendToUser(
                    savedMessage.getRecipientId(),
                    "/queue/messages",
                    new ChatNotification(
                            savedMessage.getId(),
                            savedMessage.getSenderId(),
                            savedMessage.getRecipientId(),
                            savedMessage.getSenderName()));

            messagingTemplate.convertAndSendToUser(
                    savedMessage.getSenderId(),
                    "/queue/messages",
                    new Notification(
                            savedMessage.getId(),
                            savedMessage.getSenderId(),
                            savedMessage.getRecipientId(),
                            NotificationType.READ_RECEIPT));
        }
    }
}
