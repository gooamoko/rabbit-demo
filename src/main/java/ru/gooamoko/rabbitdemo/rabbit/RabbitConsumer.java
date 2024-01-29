package ru.gooamoko.rabbitdemo.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.gooamoko.rabbitdemo.model.SmsMessage;

@Service
public class RabbitConsumer {
    private static final Logger log = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = "${notifications.sms.queue}", concurrency = "${notifications.sms.concurrency}")
    public void processSmsNotification(String message) {
        if (message == null || message.isBlank()) {
            log.warn("Received blank sms notification");
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            SmsMessage smsMessage = mapper.readValue(message, SmsMessage.class);

            // Видимо, сообщение корректное
            log.info("Received sms notification. Phone: {}, text: {}", smsMessage.getPhone(), smsMessage.getText());
        } catch (JsonProcessingException e) {
            log.error("Can't deserialize message {}.", message, e);
        }
    }

    @RabbitListener(queues = "${notifications.email.queue}", concurrency = "${notifications.email.concurrency}")
    public void processEmailNotification(String message) {
        log.info("Received email notification: {}", message);
    }
}
