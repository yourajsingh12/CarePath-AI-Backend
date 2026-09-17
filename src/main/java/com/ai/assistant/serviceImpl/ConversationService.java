package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConversationService {

    private final Map<String, List<ChatMessage>> conversations =
            new ConcurrentHashMap<>();

    public List<ChatMessage> getMessages(String conversationId) {

        return conversations.computeIfAbsent(
                conversationId,
                id -> new ArrayList<>());

    }

    public void addUserMessage(String conversationId, String message) {

        getMessages(conversationId)
                .add(new ChatMessage("user", message));

    }

    public void addAssistantMessage(String conversationId, String message) {

        getMessages(conversationId)
                .add(new ChatMessage("assistant", message));

    }

}
