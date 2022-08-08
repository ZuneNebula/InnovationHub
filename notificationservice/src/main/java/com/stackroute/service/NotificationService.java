package com.stackroute.service;

import com.stackroute.exception.NotificationAlreadyExistsException;
import com.stackroute.exception.NotificationNotFoundException;
import com.stackroute.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification saveNotification(Notification notification) throws NotificationAlreadyExistsException;

    Notification updateNotification(Notification notification) throws NotificationNotFoundException;

    List<Notification> getAllNotifications(String targetId);

    Optional<Notification> getNotificationById(String notificationId);

    List<Notification> getEveryNotification();
}
