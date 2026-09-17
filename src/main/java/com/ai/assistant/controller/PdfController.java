package com.ai.assistant.controller;

import com.ai.assistant.serviceInterface.DocumentIngestionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final DocumentIngestionServiceInterface documentIngestionService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws Exception {

        documentIngestionService.ingestPdf(file);

        return "PDF uploaded successfully.";

    }
}