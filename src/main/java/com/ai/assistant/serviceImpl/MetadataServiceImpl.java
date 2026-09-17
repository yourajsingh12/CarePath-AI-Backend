package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.DocumentUploadRequest;
import com.ai.assistant.serviceInterface.MetadataServiceInterface;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MetadataServiceImpl implements MetadataServiceInterface {

    @Override
    public Map<String, Object> build(DocumentUploadRequest request) {

        Map<String, Object> metadata = new HashMap<>();

        metadata.put("title", request.getTitle());
        metadata.put("course", request.getCourse());
        metadata.put("module", request.getModule());
        metadata.put("lesson", request.getLesson());
        metadata.put("language", request.getLanguage());

        metadata.put("source", "UPLOAD");

        metadata.put("version", 1);

        return metadata;

    }


}
