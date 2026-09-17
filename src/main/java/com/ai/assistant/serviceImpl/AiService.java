package com.ai.assistant.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public String generateResponse(String prompt) {

        return chatClient
                .prompt()
                .user(prompt)
                .options(OllamaOptions.builder()
                        .temperature(0.0)
                        .build())
                .call()
                .content();
    }
}