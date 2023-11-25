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
        if (message == null || message.isBlank()) {
            log.warn("Received blank sms notification");
            return;
        }

        String[] parts = message.split("\\|");
        if (parts.length != 2) {
            log.warn("Received message with incorrect format: {}", message);
            return;
        }
        // Видимо, сообщение корректное
        log.info("Received sms notification. Phone: {}, text: {}", parts[0], parts[1]);
    }

    @RabbitListener(queues = "${notifications.email.queue}")
    public void processEmailNotification(String message) {
        log.info("Received email notification: {}", message);
    }
}
