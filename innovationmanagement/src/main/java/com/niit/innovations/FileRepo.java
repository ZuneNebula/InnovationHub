package com.niit.innovations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepo extends MongoRepository<InnovationFile,String> {
}
