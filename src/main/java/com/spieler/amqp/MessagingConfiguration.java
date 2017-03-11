package com.spieler.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by spielerl on 05/03/2017.
 */
@Configuration
public class MessagingConfiguration {
    @Bean
    public Queue someQueue() {
        String name = "orasioQueue";
        boolean durable = false;
        boolean exclusive = false;
        boolean autoDelete = false;
        return new Queue(name, durable, exclusive, autoDelete);
    }
}
