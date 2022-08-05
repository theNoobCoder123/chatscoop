package com.example.chatscoop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.chatscoop.model.ChatMessage;
import com.example.chatscoop.model.ChatNotification;
import com.example.chatscoop.model.ChatMessage.MessageStatus;
import com.example.chatscoop.service.ChatRoomService;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        String chatId = ChatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId());
        chatMessage.setChatId(chatId);
        chatMessage.setStatus(MessageStatus.SENT);

        logger.info("dgdkgjdshkgsdgksjdhgksdg");

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages", new ChatNotification(
                chatMessage.getId(),
                chatMessage.getSenderId(),
                chatMessage.getSenderName()));
    }
}
