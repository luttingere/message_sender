package com.reaxium.messenger.modules.messages.service;

import com.reaxium.messenger.modules.messages.model.Message;
import com.reaxium.messenger.modules.messages.model.MessageDTO;
import com.reaxium.messenger.modules.messages.repository.MessageRepository;
import com.reaxium.messenger.modules.push.dto.PushMessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void createMessage(MessageDTO messageDTO) {
        messageRepository.save(Message.builder()
                .message(messageDTO.getMessage())
                .title(messageDTO.getTitle())
                .data(messageDTO.getData())
                .deviceToken(messageDTO.getDeviceToken())
                .topic(messageDTO.getTopic())
                .status(messageDTO.getStatus())
                .build());
    }

}
