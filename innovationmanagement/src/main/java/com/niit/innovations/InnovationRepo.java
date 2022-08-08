package com.niit.innovations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Configuration
public interface InnovationRepo extends MongoRepository<Innovation, String> {
    Page<Innovation> findByInnovatorId(String innovatorId, Pageable pageable);
    Page<Innovation> findByInnovatorIdAndDomain(String innovatorId, String domain, Pageable pageable);
//    Page<Innovation> allInnovations(Pageable pageable);

    //created for expert view
    Page<Innovation> findByDomain(String domain, Pageable pageable);
    Page<Innovation> findByDomainAndStatus(String domain,String status, Pageable pageable);
    Page<Innovation> findByStatus(String status, Pageable pageable);
    //Page<Innovation> findByInnovatorIdAndDateOfCreation(String innovatorId, Pageable pageable);
    @Query("{ 'innovationName' : { $regex: ?0 } }")
    List<Innovation> findByRegexpInnovationName(String regexp);

    @Query("{ 'innovationDesc' : { $regex: ?0 } }")
    List<Innovation> findByRegexpInnovationDesc(String regexp);
}
