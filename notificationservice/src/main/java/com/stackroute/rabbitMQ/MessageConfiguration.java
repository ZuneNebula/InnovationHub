package com.stackroute.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    @Bean
    Queue newProposalQueue() {
        System.out.println("inside producer setup new proposal");
        return new Queue("newProposalQueue", false);
    }

    @Bean
    Queue acceptedProposalQueue() {
        System.out.println("inside producer setup accepted proposal");
        return new Queue("acceptedProposalQueue", false);
    }

    @Bean
    Queue rejectedProposalQueue() {
        System.out.println("inside producer setup rejected proposal");
        return new Queue("rejectedProposalQueue", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding newProposalBinding(Queue newProposalQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(newProposalQueue).to(topicExchange).with("queue.newProposal");
    }

    @Bean
    Binding acceptedProposalBinding(Queue acceptedProposalQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(acceptedProposalQueue).to(topicExchange).with("queue.acceptedProposal");
    }

    @Bean
    Binding rejectedProposalBinding(Queue rejectedProposalQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(rejectedProposalQueue).to(topicExchange).with("queue.rejectedProposal");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

}
