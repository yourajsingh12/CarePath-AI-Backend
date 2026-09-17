package com.ai.assistant.serviceInterface;

import com.ai.assistant.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageInterface
        extends JpaRepository<ChatMessageEntity, Long> {

    List<ChatMessageEntity> findByConversationConversationIdOrderByCreatedAtAsc(
            String conversationId
    );


}
