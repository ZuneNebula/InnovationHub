package com.stackroute.expert;

import com.stackroute.exception.ExpertAlreadyExistsException;
import com.stackroute.exception.ExpertNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ExpertService {
    Expert saveExpert(Expert expert) throws ExpertAlreadyExistsException;

    List<Expert> getExperts();

    Optional<Expert> getExpertByUsername(String username) throws ExpertNotFoundException;

    Expert getExpertById(String id) throws ExpertNotFoundException;

    Expert updateExpert(Expert expert) throws ExpertNotFoundException;
}
