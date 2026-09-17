package com.ai.assistant.dto;

import lombok.Data;

@Data
public class DocumentUploadRequest {

    private String title;

    private String content;

    private String course;

    private String module;

    private String lesson;

    private String language;

}
