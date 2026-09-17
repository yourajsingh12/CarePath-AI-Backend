package com.ai.assistant.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(

        @NotBlank
        String conversationId,

        @NotBlank
        String message,

        String course,

        String module,

        String lesson,

        String language

) {
}