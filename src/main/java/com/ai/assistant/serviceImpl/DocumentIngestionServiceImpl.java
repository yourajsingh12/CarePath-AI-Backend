package com.ai.assistant.serviceImpl;

import com.ai.assistant.serviceImpl.ChunkService;
import com.ai.assistant.serviceInterface.DocumentIngestionServiceInterface;
import com.ai.assistant.serviceInterface.PdfExtractor;
import com.ai.assistant.vector.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DocumentIngestionServiceImpl
        implements DocumentIngestionServiceInterface {

    private final PdfExtractor pdfExtractor;
    private final ChunkService chunkService;
    private final VectorStoreService vectorStoreService;

    @Override
    public void ingestPdf(MultipartFile file) throws Exception {

        String text = pdfExtractor.extract(file);

        List<Document> chunks = chunkService.chunk(text);

        List<Document> documents = new ArrayList<>();

        String documentId = UUID.randomUUID().toString();

        for (int i = 0; i < chunks.size(); i++) {

            Document chunk = chunks.get(i);

            Map<String, Object> metadata = new HashMap<>();

            metadata.put("documentId", documentId);
            metadata.put("fileName", file.getOriginalFilename());
            metadata.put("chunkNumber", i + 1);
            metadata.put("totalChunks", chunks.size());
            metadata.put("source", "PDF");

            Document document = new Document(
                    chunk.getText(),
                    metadata
            );

            documents.add(document);
        }

        vectorStoreService.saveDocuments(documents);

        System.out.println("Chunks Stored : " + documents.size());
    }

}
