package com.stackroute.controller;

import com.stackroute.exception.NotificationNotFoundException;
import com.stackroute.model.Notification;
import com.stackroute.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    private ResponseEntity responseEntity;

    @GetMapping({"/"})
    public ResponseEntity<List<Notification>> getEveryNotifications(){
        List<Notification> notifications = this.notificationService.getEveryNotification();
        this.responseEntity = new ResponseEntity(notifications, HttpStatus.OK);
        return this.responseEntity;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable("id") String targetId) {
        try {
            List<Notification> notifications = this.notificationService.getAllNotifications(targetId);
            this.responseEntity = new ResponseEntity(notifications, HttpStatus.OK);
        } catch (Exception var2) {
            this.responseEntity = new ResponseEntity("error while fetching notifications", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return this.responseEntity;
    }

    @GetMapping({"/id/{id}"})
    public ResponseEntity<List<Notification>> getNotificationById(@PathVariable("id") String notificationId) {
        try {
            Optional<Notification> notification = this.notificationService.getNotificationById(notificationId);
            this.responseEntity = new ResponseEntity(notification, HttpStatus.OK);
        }
        catch (Exception var2) {
            this.responseEntity = new ResponseEntity("error while fetching notifications", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return this.responseEntity;
    }

    @PutMapping({"/"})
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) throws NotificationNotFoundException {
        try {
            Notification notificationData = this.notificationService.updateNotification(notification);
            this.responseEntity = new ResponseEntity(notificationData, HttpStatus.ACCEPTED);
        } catch (NotificationNotFoundException var3) {
            throw new NotificationNotFoundException();
        } catch (Exception var4) {
            this.responseEntity = new ResponseEntity("error while updating notification data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return this.responseEntity;
    }


}
