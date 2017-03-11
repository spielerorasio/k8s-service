package com.spieler.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by spielerl on 05/03/2017.
 */
@Component
public class MessagingProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingProducer.class);

    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private Queue queue;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void createQueue() {
        this.amqpAdmin.declareQueue(queue);
    }

    //    @Scheduled(fixedDelay = 1000L)
    public void send(String message) {
        LOGGER.info("sending message : {}", message);

        this.rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
