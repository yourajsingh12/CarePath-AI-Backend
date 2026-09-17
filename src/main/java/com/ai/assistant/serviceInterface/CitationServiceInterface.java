package com.ai.assistant.serviceInterface;

import com.ai.assistant.dto.KnowledgeSource;
import org.springframework.ai.document.Document;

import java.util.List;

public interface CitationServiceInterface {

    List<KnowledgeSource> buildSources(List<Document> documents);

}
