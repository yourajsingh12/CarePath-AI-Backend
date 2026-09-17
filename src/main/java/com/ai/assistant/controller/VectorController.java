package com.ai.assistant.controller;

import com.ai.assistant.dto.KnowledgeRequest;
import com.ai.assistant.vector.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.ai.document.Document;
import java.util.List;

@RestController
@RequestMapping("/api/vector")
@RequiredArgsConstructor
public class VectorController {

    private final VectorStoreService vectorStoreService;

    @PostMapping("/save")
    public String save() {

        Document document = new Document("""
            SimpliMet Internal Rule:

            Every diabetes patient must complete Module 7 before the final assessment.

            Secret Code: SIMPLI-2026-HEALTH
            """);

        vectorStoreService.saveDocument(document);

        return "Document Saved Successfully";
    }


    @GetMapping("/search")
    public List<Document> search(@RequestParam String query) {

        KnowledgeRequest request = KnowledgeRequest.builder()
                .question(query)
                .build();

        return vectorStoreService.search(request);
    }

}
