package com.ai.assistant.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class DocumentMetadata {

    private UUID documentId;

    private String fileName;

    private String title;

    private String source;

    private Integer totalChunks;

    private LocalDateTime uploadedAt;

}
