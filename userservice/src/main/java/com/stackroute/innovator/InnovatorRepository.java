package com.stackroute.innovator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface InnovatorRepository extends MongoRepository<Innovator, String> {

    @Query(value = "{innovatorId : ?0}")
    Innovator findInnovatorById(String innovatorId);

}
