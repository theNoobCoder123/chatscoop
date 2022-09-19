package com.example.chatscoop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatMessageIdService {
    @Autowired
    private final RestTemplate restTemplate;

    private String ipAddress;
    private String port;
    private String protocol;

    public ChatMessageIdService(RestTemplateBuilder restTemplateBuilder,
            @Value("${id-generator.address}") String ipAddress,
            @Value("${id-generator.port}") String port,
            @Value("${id-generator.protocol}") String protocol) {
        this.restTemplate = restTemplateBuilder.build();
        this.ipAddress = ipAddress;
        this.port = port;
        this.protocol = protocol;
    }

    public String getChatId() {
        String url = this.protocol + "://" + this.ipAddress + ":" + this.port + "/newId";
        return this.restTemplate.getForObject(url, String.class);
    }
}
