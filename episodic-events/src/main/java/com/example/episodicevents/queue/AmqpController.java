package com.example.episodicevents.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer3 on 5/23/17.
 */
@RestController
public class AmqpController {

    private final RabbitTemplate rabbitTemplate;

    public AmqpController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/amqp")
    public void publishEvent(@RequestBody String body){
        rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", new MyCustomMessage(body, 42));
    }

    public static class MyCustomMessage {
        private final String sentence;

        private final int someNumber;

        public MyCustomMessage(String sentence, int someNumber) {
            this.sentence = sentence;
            this.someNumber = someNumber;
        }

        public String getSentence() {
            return sentence;
        }
        public int getSomeNumber() {
            return someNumber;
        }

    }
}
