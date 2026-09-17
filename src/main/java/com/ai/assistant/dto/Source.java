package com.ai.assistant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Source {

    private String fileName;

    private Integer chunkNumber;

}
