package com.stackroute.repository;

import com.stackroute.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,String> {

    @Query(value = "{targetId : ?0}")
    List<Notification> findByTargetId(String targetId);

}
