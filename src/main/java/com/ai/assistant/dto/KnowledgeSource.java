package com.ai.assistant.dto;

import lombok.Builder;

@Builder
public record KnowledgeSource(

        String documentId,

        String fileName,

        Integer chunkNumber

) {
}