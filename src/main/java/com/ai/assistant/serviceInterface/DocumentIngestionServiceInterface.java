package com.ai.assistant.serviceInterface;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentIngestionServiceInterface {

    void ingestPdf(MultipartFile file) throws Exception;

}
