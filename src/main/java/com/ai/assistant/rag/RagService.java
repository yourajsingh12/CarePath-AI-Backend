package com.ai.assistant.rag;

import com.ai.assistant.dto.ChatMessage;
import com.ai.assistant.dto.ChatRequest;
import com.ai.assistant.dto.ChatResponse;
import com.ai.assistant.dto.KnowledgeRequest;
import com.ai.assistant.serviceImpl.AiService;
import com.ai.assistant.serviceImpl.PromptBuilderService;
import com.ai.assistant.serviceInterface.CitationServiceInterface;
import com.ai.assistant.serviceInterface.KnowledgeServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RagService {

    private final KnowledgeServiceInterface knowledgeService;
    private final PromptBuilderService promptBuilderService;
    private final AiService aiService;
    private final CitationServiceInterface citationService;

    public ChatResponse ask(String systemPrompt,
                            List<ChatMessage> history,
                            ChatRequest chatRequest) {

        KnowledgeRequest request = KnowledgeRequest.builder()
                .question(chatRequest.message())
                .course(chatRequest.course())
                .module(chatRequest.module())
                .lesson(chatRequest.lesson())
                .language(chatRequest.language())
                .build();

        List<Document> documents = knowledgeService.retrieve(request);

        log.info("Retrieved Documents : {}", documents.size());

        documents.forEach(document -> {
            log.info("---------------------------------------");
            log.info("Metadata : {}", document.getMetadata());
            log.info("Content : {}", document.getText());
        });

        // No documents found
        if (documents == null || documents.isEmpty()) {

            return new ChatResponse(
                    "The uploaded documents do not contain enough information.",
                    List.of()
            );

        }

        StringBuilder context = new StringBuilder();

        for (Document document : documents) {

            context.append(document.getText())
                    .append("\n\n");

        }

        String prompt = promptBuilderService.buildPrompt(
                systemPrompt,
                history,
                context.toString(),
                chatRequest.message()
        );

        log.info("Generated Prompt:\n{}", prompt);

        String answer = aiService.generateResponse(prompt);

        return new ChatResponse(
                answer,
                citationService.buildSources(documents)
        );
    }
}