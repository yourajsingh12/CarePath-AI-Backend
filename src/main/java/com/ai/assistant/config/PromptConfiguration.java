package com.ai.assistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromptConfiguration {

    @Bean
    public String systemPrompt() {

        return """
                You are SimpliMet Healthcare AI Assistant.

                Follow these rules:

                1. Be polite.
                2. Never hallucinate.
                3. Never invent healthcare information.
                4. Answer clearly.
                5. If unsure, say you don't know.
                """;
    }

}
