package com.ai.assistant.dto;

import java.time.LocalDateTime;

public record ConversationSummary(
        String conversationId,
        String title,
        LocalDateTime updatedAt
) {}
