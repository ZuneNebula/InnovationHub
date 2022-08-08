package com.stackroute.rabbitMQ;

import com.stackroute.model.Notification;
import com.stackroute.model.NotificationData;
import com.stackroute.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    NotificationService notificationService;

    @RabbitListener(queues="newProposalQueue")
    public void getNotificationDtoFromNewProposal(EventDTO eventDTO) throws Exception
    {
        System.out.println("Inside consumer");
        System.out.println(eventDTO.getEvent());
        Notification notification = new Notification();
        notification.setSourceType("Expert");
        notification.setSourceId(eventDTO.getEventDataDTO().getExpertId());
        notification.setTargetType("Innovator");
        notification.setTargetId(eventDTO.getEventDataDTO().getInnovatorId());
        notification.setStatus("Unread");
        notification.setInnovationId(eventDTO.getEventDataDTO().getInnovationId());
        notification.setProposalId(eventDTO.getEventDataDTO().getProposalId());
        notification.setDate(eventDTO.getEvent_ts());
        notification.setNotificationData(new NotificationData("New proposal", "You have a new Proposal"));
        notificationService.saveNotification(notification);
    }

    @RabbitListener(queues="acceptedProposalQueue")
    public void getNotificationDtoFromAcceptedProposal(EventDTO eventDTO) throws Exception
    {
        System.out.println("Inside consumer");
        System.out.println(eventDTO.getEvent());
        Notification notification = new Notification();
        notification.setTargetType("Expert");
        notification.setTargetId(eventDTO.getEventDataDTO().getExpertId());
        notification.setSourceType("Innovator");
        notification.setSourceId(eventDTO.getEventDataDTO().getInnovatorId());
        notification.setStatus("Unread");
        notification.setInnovationId(eventDTO.getEventDataDTO().getInnovationId());
        notification.setProposalId(eventDTO.getEventDataDTO().getProposalId());
        notification.setDate(eventDTO.getEvent_ts());
        notification.setNotificationData(new NotificationData("accepted", "Your proposal was accepted"));
        notificationService.saveNotification(notification);
    }

    @RabbitListener(queues="rejectedProposalQueue")
    public void getNotificationDtoFromRejectedProposal(EventDTO eventDTO) throws Exception
    {
        System.out.println("Inside consumer");
        System.out.println(eventDTO.getEvent());
        Notification notification = new Notification();
        notification.setTargetType("Expert");
        notification.setTargetId(eventDTO.getEventDataDTO().getExpertId());
        notification.setSourceType("Innovator");
        notification.setSourceId(eventDTO.getEventDataDTO().getInnovatorId());
        notification.setStatus("Unread");
        notification.setInnovationId(eventDTO.getEventDataDTO().getInnovationId());
        notification.setProposalId(eventDTO.getEventDataDTO().getProposalId());
        notification.setDate(eventDTO.getEvent_ts());
        notification.setNotificationData(new NotificationData("rejected", "Your proposal was rejected"));
        notificationService.saveNotification(notification);
    }


}
