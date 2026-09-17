package com.ai.assistant.controller;

import com.ai.assistant.dto.ChatMessage;
import com.ai.assistant.dto.ChatRequest;
import com.ai.assistant.dto.ChatResponse;
import com.ai.assistant.dto.ConversationSummary;
import com.ai.assistant.response.ApiResponse;
import com.ai.assistant.serviceImpl.ChatServiceImpl;
import com.ai.assistant.serviceImpl.ConversationPersistenceServiceImpl;
import com.ai.assistant.serviceImpl.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatServiceImpl chatServiceImpl;
    private final ConversationPersistenceServiceImpl conversationPersistenceService;

    @PostMapping
    public ApiResponse<ChatResponse> chat(
            @Valid
            @RequestBody ChatRequest request) {

        ChatResponse response = chatServiceImpl.chat(request);

        return ApiResponse.<ChatResponse>builder()
                .success(true)
                .message("Request processed successfully")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/history/{conversationId}")
    public ApiResponse<List<ChatMessage>> history(
            @PathVariable String conversationId) {

        return ApiResponse.<List<ChatMessage>>builder()
                .success(true)
                .message("Conversation history fetched successfully")
                .data(conversationPersistenceService.getConversationHistory(conversationId))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/conversations")
    public ApiResponse<List<ConversationSummary>> conversations() {

        return ApiResponse.<List<ConversationSummary>>builder()
                .success(true)
                .message("Conversations fetched successfully")
                .data(conversationPersistenceService.getAllConversations())
                .timestamp(LocalDateTime.now())
                .build();
    }
}