package com.ai.assistant.serviceImpl;

import com.ai.assistant.serviceInterface.ChunkServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkServiceImpl implements ChunkServiceInterface {

    private static final int CHUNK_SIZE = 500;

    @Override
    public List<String> chunk(String text) {

        List<String> chunks = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return chunks;
        }

        int start = 0;

        while (start < text.length()) {

            int end = Math.min(start + CHUNK_SIZE, text.length());

            chunks.add(text.substring(start, end));

            start = end;
        }

        return chunks;
    }

}
