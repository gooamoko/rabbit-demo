package ru.gooamoko.rabbitdemo.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {
    private static final Logger log = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = "${notifications.sms.queue}")
    public void processSmsNotification(String message) {
        log.info("Received sms notification: {}", message);
    }

    @RabbitListener(queues = "${notifications.email.queue}")
    public void processEmailNotification(String message) {
        log.info("Received email notification: {}", message);
    }
}
