package com.ai.assistant.serviceImpl;


import com.ai.assistant.serviceInterface.PdfExtractor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PdfExtractorImpl implements PdfExtractor {

    private final Tika tika = new Tika();

    @Override
    public String extract(MultipartFile file) throws Exception {

        log.info("Extracting text from PDF : {}", file.getOriginalFilename());

        return tika.parseToString(file.getInputStream());

    }

}
