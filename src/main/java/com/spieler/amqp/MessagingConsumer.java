package com.spieler.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by spielerl on 05/03/2017.
 */
@Component
public class MessagingConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingConsumer.class);


    @RabbitListener(queues = "orasioQueue")
    public void consume(String payload) {
        LOGGER.info("received message : {}", payload);
    }

}
