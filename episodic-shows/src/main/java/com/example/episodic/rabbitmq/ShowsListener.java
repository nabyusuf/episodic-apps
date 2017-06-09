package com.example.episodic.rabbitmq;

import com.example.episodic.viewings.ViewingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Created by trainer3 on 5/23/17.
 */
@Configuration
public class ShowsListener implements RabbitListenerConfigurer {

    private final ViewingService viewingService;
    //private final ObjectMapper mapper;

    public ShowsListener(ViewingService viewingService) {
        this.viewingService = viewingService;
    }

    @RabbitListener(queues = "episodic-progress")
    @Transactional
    public void receiveMessage(final ShowMessage message) {
        viewingService.findViewingByEpisodeId(message);

        System.out.println("************************************************");
        System.out.println(message.toString());
        System.out.println("************************************************");

    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        //messageConverter.setObjectMapper(mapper);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
