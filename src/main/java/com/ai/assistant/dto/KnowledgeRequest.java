package com.ai.assistant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KnowledgeRequest {

    private String question;

    private String course;

    private String module;

    private String lesson;

    private String language;

}