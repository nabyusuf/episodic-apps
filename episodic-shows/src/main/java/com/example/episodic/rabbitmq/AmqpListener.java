package com.example.episodic.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.Map;

/**
 * Created by trainer3 on 5/23/17.
 */
@Configuration
public class AmqpListener implements RabbitListenerConfigurer {

    @RabbitListener(queues = "my-queue")
    public void receiveMessage(final HelloMessage message) {
        System.out.println("************************************************");
        System.out.println(message.toString());
        System.out.println("************************************************");
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    public static class HelloMessage {
        public String hello;

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }

        @Override
        public String toString() {
            return "HelloMessage{" +
                    "hello='" + hello + '\'' +
                    '}';
        }
    }

}