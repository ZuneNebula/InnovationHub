package com.stackroute.expert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ExpertRepository extends MongoRepository<Expert, String> {

    @Query(value = "{expertId : ?0}")
    Expert findExpertById(String expertId);
}
