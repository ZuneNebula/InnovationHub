package com.niit.rabbitMQ;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private TopicExchange exchange;
    private String[] routingKey = {"queue.newProposal", "queue.acceptedProposal", "queue.rejectedProposal"};


    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, TopicExchange exchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendProposalToRabbitMq(EventDTO eventDTO)
    {
        System.out.println("Inside producer");
        System.out.println("exchange name" + exchange.getName() + " routing key " + routingKey[0]);
        System.out.println(eventDTO.getEvent());
        rabbitTemplate.convertAndSend(exchange.getName(),routingKey[0] ,eventDTO);
    }

    public void sendAcceptedProposalToRabbitMq(EventDTO eventDTO)
    {
        System.out.println("Inside producer");
        System.out.println("exchange name" + exchange.getName() + " routing key " + routingKey[1]);
        System.out.println(eventDTO.getEvent());
        rabbitTemplate.convertAndSend(exchange.getName(),routingKey[1] ,eventDTO);
    }

    public void sendRejectedProposalToRabbitMq(EventDTO eventDTO)
    {
        System.out.println("Inside producer");
        System.out.println("exchange name" + exchange.getName() + " routing key " + routingKey[2]);
        System.out.println(eventDTO.getEvent());
        rabbitTemplate.convertAndSend(exchange.getName(),routingKey[2] ,eventDTO);
    }
}
