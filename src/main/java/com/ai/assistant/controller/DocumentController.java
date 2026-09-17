package com.ai.assistant.controller;

import com.ai.assistant.dto.DocumentUploadRequest;
import com.ai.assistant.serviceInterface.DocumentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentServiceInterface documentService;

    @PostMapping("/upload")
    public String upload(
            @RequestBody DocumentUploadRequest request) {

        documentService.upload(request);

        return "Uploaded Successfully";
    }

}
