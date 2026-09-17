package com.ai.assistant.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SourceResponse {

    private String answer;

    private List<Source> sources;

}
