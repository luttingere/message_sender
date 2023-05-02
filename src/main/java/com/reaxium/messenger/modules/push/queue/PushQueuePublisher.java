package com.reaxium.messenger.modules.push.queue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PushQueuePublisher {

    @Value("${push.notification.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${push.notification.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PushQueuePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void queueMessage(String message) {
        log.debug("Queueing Push notification message <" + message + ">");
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
    }

}
