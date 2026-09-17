package com.ai.assistant.serviceImpl;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChunkService {

    private final TokenTextSplitter splitter = TokenTextSplitter.builder()
            .withChunkSize(100)
            .withMinChunkSizeChars(100)
            .withMinChunkLengthToEmbed(20)
            .withMaxNumChunks(10000)
            .withKeepSeparator(true)
            .build();

    public List<Document> chunk(String text) {

        Document document = new Document(text);

        return splitter.apply(List.of(document));

    }
}