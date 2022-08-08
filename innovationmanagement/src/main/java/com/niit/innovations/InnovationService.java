package com.niit.innovations;

import com.niit.exception.InnovationAlreadyExistsException;
import com.niit.exception.InnovationNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InnovationService {
    boolean deleteInnovation(String innovationId) throws InnovationNotFoundException;
    boolean updateInnovation(Innovation innovation) throws InnovationNotFoundException;
    Innovation saveInnovation(Innovation innovation) throws InnovationAlreadyExistsException, IOException;
    Innovation getInnovationById(String innovationId) throws InnovationNotFoundException;
//    List<Innovation> getAllInnovations();
    Map<String, Object> getAllInnovations(int page, int size, boolean recent);

    Map<String, Object> getInnovationByFiler(String innovatorId,int page, int size,String domain);

    //for experts view
    Map<String, Object> filterInnovationsByDomainAndStatus(String status,int page, int size,String[] domain,String search, boolean recent);

    List<Innovation> searchInnovations(String query);
}
