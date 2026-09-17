package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.KnowledgeSource;
import com.ai.assistant.serviceInterface.CitationServiceInterface;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitationServiceImpl implements CitationServiceInterface {

    @Override
    public List<KnowledgeSource> buildSources(List<Document> documents) {

        List<KnowledgeSource> sources = new ArrayList<>();

        for (Document document : documents) {

            sources.add(
                    KnowledgeSource.builder()
                            .documentId(String.valueOf(document.getMetadata().get("documentId")))
                            .fileName(String.valueOf(document.getMetadata().get("fileName")))
                            .chunkNumber(
                                    Integer.parseInt(
                                            String.valueOf(document.getMetadata().get("chunkNumber"))
                                    )
                            )
                            .build()
            );
        }

        return sources;
    }
}