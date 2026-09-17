package com.ai.assistant.vector;

import com.ai.assistant.dto.KnowledgeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VectorStoreService {

    private final VectorStore vectorStore;

    public void saveDocument(Document document) {
        vectorStore.add(List.of(document));
    }

    public void saveDocuments(List<Document> documents) {
        vectorStore.add(documents);
    }

    // For testing
    public List<Document> search(String query) {

        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(3)
                        .build()
        );
    }

    // For RAG
    public List<Document> search(KnowledgeRequest request) {

        SearchRequest.Builder builder = SearchRequest.builder()
                .query(request.getQuestion())
                .topK(3);

        if (request.getCourse() != null && !request.getCourse().isBlank()) {
            builder.filterExpression("course == '" + request.getCourse() + "'");
        }

        // Metadata filter will be added here later

        return vectorStore.similaritySearch(builder.build());
    }
}