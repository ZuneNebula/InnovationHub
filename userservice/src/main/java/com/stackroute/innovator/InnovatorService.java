package com.stackroute.innovator;

import com.stackroute.exception.InnovatorAlreadyExistsException;
import com.stackroute.exception.InnovatorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface InnovatorService {
    Innovator saveInnovator(Innovator innovator) throws InnovatorAlreadyExistsException;

    List<Innovator> getInnovators();

    Optional<Innovator> getInnovatorByUsername(String username) throws InnovatorNotFoundException;

    Innovator getInnovatorById(String id) throws InnovatorNotFoundException;

    Innovator updateInnovator(Innovator innovator) throws InnovatorNotFoundException;
}

