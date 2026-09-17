package com.ai.assistant.serviceInterface;


import com.ai.assistant.dto.KnowledgeRequest;
import org.springframework.ai.document.Document;

import java.util.List;

public interface KnowledgeServiceInterface {

    List<Document> retrieve(KnowledgeRequest request);


}
