package com.ai.assistant.dto;

import java.util.List;

public record ChatResponse(

        String response,

        List<KnowledgeSource> sources

) {}