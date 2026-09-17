package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptBuilderService {

    public String buildPrompt(String systemPrompt,
                              List<ChatMessage> history,
                              String context,
                              String userMessage) {

        StringBuilder prompt = new StringBuilder();

        // System Prompt
        prompt.append(systemPrompt);

        prompt.append("\n\n");
        prompt.append("=====================================\n");
        prompt.append("RETRIEVED CONTEXT\n");
        prompt.append("=====================================\n");

        if (context != null && !context.isBlank()) {

            prompt.append(context);

        } else {

            prompt.append("No relevant context found.");

        }

        prompt.append("\n\n");
        prompt.append("=====================================\n");
        prompt.append("CONVERSATION HISTORY\n");
        prompt.append("=====================================\n");

        if (history != null && !history.isEmpty()) {

            for (ChatMessage chat : history) {

                prompt.append(chat.getRole())
                        .append(": ")
                        .append(chat.getContent())
                        .append("\n");
            }

        } else {

            prompt.append("No previous conversation.");

        }

        prompt.append("\n\n");
        prompt.append("=====================================\n");
        prompt.append("CURRENT USER QUESTION\n");
        prompt.append("=====================================\n");

        prompt.append(userMessage);

        prompt.append("""

                =====================================
                RESPONSE INSTRUCTIONS
                =====================================

                Answer ONLY using the RETRIEVED CONTEXT.

                If the answer is not present in the context,
                reply exactly:

                "The uploaded documents do not contain enough information."

                Do NOT use your own knowledge.
                """);

        return prompt.toString();
    }
}