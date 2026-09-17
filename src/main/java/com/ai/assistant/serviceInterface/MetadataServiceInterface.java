package com.ai.assistant.serviceInterface;

import com.ai.assistant.dto.DocumentUploadRequest;

import java.util.Map;

public interface MetadataServiceInterface {

    Map<String, Object> build(DocumentUploadRequest request);

}
