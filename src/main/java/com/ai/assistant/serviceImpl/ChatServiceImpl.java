package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.ChatRequest;
import com.ai.assistant.dto.ChatResponse;
import com.ai.assistant.rag.RagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl {

    private final PromptService promptService;
    private final ConversationPersistenceServiceImpl conversationPersistenceService;
    private final RagService ragService;

    public ChatResponse chat(ChatRequest request) {

        log.info("Conversation ID : {}", request.conversationId());
        log.info("User Message : {}", request.message());

        // Save user message
        conversationPersistenceService.saveUserMessage(
                request.conversationId(),
                request.message()
        );

        // Generate AI response
        ChatResponse response = ragService.ask(
                promptService.systemPrompt(),
                conversationPersistenceService.getConversationHistory(
                        request.conversationId()
                ),
                request
        );

        // Save AI response
        conversationPersistenceService.saveAssistantMessage(
                request.conversationId(),
                response.response()
        );
        log.info("AI Response generated successfully.");
        return response;
    }
}