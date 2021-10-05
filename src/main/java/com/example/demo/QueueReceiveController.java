package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiveController {
    @Autowired
    private JmsTemplate jmsTemplate;
    private static final String DESTINATION_NAME = "demoqueue";

    private static final String QUEUE_NAME = "gopi-queue";

    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(User user) {
        logger.info("Received message: {}", user.getName());
        jmsTemplate.convertAndSend(DESTINATION_NAME, "Hello World");
        
    }
}