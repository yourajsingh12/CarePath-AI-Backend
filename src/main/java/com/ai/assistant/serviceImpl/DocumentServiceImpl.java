package com.ai.assistant.serviceImpl;
import com.ai.assistant.dto.DocumentUploadRequest;
import com.ai.assistant.serviceInterface.DocumentServiceInterface;
import com.ai.assistant.serviceInterface.MetadataServiceInterface;
import com.ai.assistant.vector.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentServiceInterface {

    private final ChunkService chunkService;
    private final MetadataServiceInterface metadataService;
    private final VectorStoreService vectorStoreService;

    @Override
    public void upload(DocumentUploadRequest request) {

        List<Document> chunks = chunkService.chunk(request.getContent());

        Map<String, Object> metadata = metadataService.build(request);

        for (Document chunk : chunks) {

            Document document = new Document(
                    chunk.getText(),
                    metadata
            );

            vectorStoreService.saveDocument(document);
        }

        System.out.println("Total Chunks : " + chunks.size());

        chunks.forEach(System.out::println);
    }
}