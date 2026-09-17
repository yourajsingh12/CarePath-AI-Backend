package com.ai.assistant.serviceImpl;

import com.ai.assistant.dto.ChatMessage;
import com.ai.assistant.dto.ConversationSummary;
import com.ai.assistant.entity.ChatMessageEntity;
import com.ai.assistant.entity.Conversation;
import com.ai.assistant.serviceInterface.ChatMessageInterface;
import com.ai.assistant.serviceInterface.ConversationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationPersistenceServiceImpl {

    private final ConversationInterface conversationRepository;
    private final ChatMessageInterface chatMessageRepository;

    public Conversation findOrCreate(String conversationId) {

        return conversationRepository
                .findByConversationId(conversationId)
                .orElseGet(() -> {

                    Conversation conversation = Conversation.builder()
                            .conversationId(conversationId)
                            .title("New Conversation")
                            .build();

                    return conversationRepository.save(conversation);

                });
    }

    public void saveUserMessage(String conversationId, String message) {

        Conversation conversation = findOrCreate(conversationId);

        ChatMessageEntity entity = ChatMessageEntity.builder()
                .conversation(conversation)
                .role("user")
                .content(message)
                .build();

        chatMessageRepository.save(entity);
    }

    public void saveAssistantMessage(String conversationId, String message) {

        Conversation conversation = findOrCreate(conversationId);

        ChatMessageEntity entity = ChatMessageEntity.builder()
                .conversation(conversation)
                .role("assistant")
                .content(message)
                .build();

        chatMessageRepository.save(entity);
    }

    public List<ChatMessage> getConversationHistory(String conversationId) {

        return chatMessageRepository
                .findByConversationConversationIdOrderByCreatedAtAsc(conversationId)
                .stream()
                .map(message -> new ChatMessage(
                        message.getRole(),
                        message.getContent()
                ))
                .toList();
    }

    public List<ConversationSummary> getAllConversations() {

        return conversationRepository
                .findAllByOrderByUpdatedAtDesc()
                .stream()
                .map(conversation -> new ConversationSummary(
                        conversation.getConversationId(),
                        conversation.getTitle(),
                        conversation.getUpdatedAt()
                ))
                .toList();
    }
}