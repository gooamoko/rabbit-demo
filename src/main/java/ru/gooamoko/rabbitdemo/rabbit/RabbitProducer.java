package ru.gooamoko.rabbitdemo.rabbit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void send(String message) {
        rabbitTemplate.convertAndSend("testQueue", message);
    }
}
