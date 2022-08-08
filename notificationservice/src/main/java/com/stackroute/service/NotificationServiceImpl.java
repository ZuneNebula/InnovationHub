package com.stackroute.service;

import com.stackroute.exception.NotificationAlreadyExistsException;
import com.stackroute.exception.NotificationNotFoundException;
import com.stackroute.model.Notification;
import com.stackroute.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notification) throws NotificationAlreadyExistsException{
//        if(this.notificationRepository.findById(notification.getNotificationId()).isPresent()){
//            throw new NotificationAlreadyExistsException();
//        }

            UUID uuid = UUID.randomUUID();
            notification.setNotificationId(String.valueOf(uuid));
            return this.notificationRepository.save(notification);

    }

    @Override
    public Notification updateNotification(Notification notification) throws NotificationNotFoundException{
        if(!this.notificationRepository.findById(notification.getNotificationId()).isPresent()){
            throw new NotificationNotFoundException();
        }
        else{
            return this.notificationRepository.save(notification);
        }
    }

    @Override
    public List<Notification> getAllNotifications(String targetId){
        List<Notification> notifs = this.notificationRepository.findByTargetId(targetId);
        List<Notification> filteredNotifs= new ArrayList<>();
        for(Notification n: notifs){
            if(n.getStatus().equals("Unread")){
                filteredNotifs.add(n);
            }
        }
        return filteredNotifs;
    }

    @Override
    public List<Notification> getEveryNotification(){
        return this.notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(String notificationId){
        return this.notificationRepository.findById(notificationId);
    }
}
