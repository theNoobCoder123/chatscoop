package com.example.chatscoop.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.chatscoop.model.ChatMessage;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessage, String> {
    List<ChatMessage> findByLastname(String lastname);
  }