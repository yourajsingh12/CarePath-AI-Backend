package com.ai.assistant.serviceInterface;

import org.springframework.web.multipart.MultipartFile;

public interface PdfExtractor {

    String extract(MultipartFile file) throws Exception;

}
