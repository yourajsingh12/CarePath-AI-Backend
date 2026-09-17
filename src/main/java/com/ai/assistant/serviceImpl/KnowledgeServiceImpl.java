package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.KnowledgeRequest;
import com.ai.assistant.serviceInterface.KnowledgeServiceInterface;
import com.ai.assistant.vector.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowledgeServiceImpl implements KnowledgeServiceInterface {

    private final VectorStoreService vectorStoreService;

    @Override
    public List<Document> retrieve(KnowledgeRequest request) {

        return vectorStoreService.search(request);

    }
}