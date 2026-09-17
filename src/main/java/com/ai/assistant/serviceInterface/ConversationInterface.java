package com.ai.assistant.serviceInterface;

import com.ai.assistant.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationInterface
        extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByConversationId(String conversationId);
    List<Conversation> findAllByOrderByUpdatedAtDesc();
}
