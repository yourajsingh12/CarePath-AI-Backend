package com.ai.assistant.controller;


import com.ai.assistant.serviceInterface.ChunkServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chunk")
@RequiredArgsConstructor
public class ChunkController {

    private final ChunkServiceInterface chunkService;

    @PostMapping
    public List<String> chunk(
            @RequestBody String text) {

        return chunkService.chunk(text);

    }

}
