package com.reaxium.messenger.modules.push.queue;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PushQueueConfiguration {

    @Value("${push.notification.queue.name}")
    private String queueName;

    @Value("${push.notification.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${push.notification.routing.key}")
    private String routingKey;

    @Bean
    Queue pushNotificationQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange pushNotificationExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding pushNotificationQueueBinding(Queue pushNotificationQueue, TopicExchange pushNotificationExchange) {
        return BindingBuilder.bind(pushNotificationQueue).to(pushNotificationExchange).with(routingKey);
    }

    @Bean
    SimpleMessageListenerContainer pushNotificationQueueContainer(ConnectionFactory connectionFactory,
                                                                  MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(PushQueueReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
