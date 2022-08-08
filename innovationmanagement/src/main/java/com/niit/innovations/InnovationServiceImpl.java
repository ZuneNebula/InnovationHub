package com.niit.innovations;

import com.niit.exception.InnovationAlreadyExistsException;
import com.niit.exception.InnovationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class InnovationServiceImpl implements InnovationService {
    @Autowired
    public InnovationRepo innovationRepo;

    @Override
    public boolean deleteInnovation(String innovationId) throws InnovationNotFoundException {
        if(innovationRepo.existsById( innovationId)) {
            innovationRepo.deleteById(innovationId);
            return true;
        }
        throw new InnovationNotFoundException();
    }

    @Override
    public boolean updateInnovation(Innovation innovation) throws InnovationNotFoundException{
        if(innovationRepo.existsById( innovation.getInnovationId())) {
            innovationRepo.save(innovation);
            return true;
        }
        throw new InnovationNotFoundException();
    }

    @Override
    public Innovation saveInnovation(Innovation innovation) throws InnovationAlreadyExistsException, IOException {
        if(!innovationRepo.existsById(innovation.getInnovationId())){
            UUID uuid = UUID.randomUUID();
            innovation.setInnovationId(String.valueOf(uuid));
            return innovationRepo.save(innovation);
        }
        throw new InnovationAlreadyExistsException();
    }

    @Override
    public Innovation getInnovationById(String innovationId) throws InnovationNotFoundException {
        if(innovationRepo.findById(innovationId).isPresent()){
            return innovationRepo.findById(innovationId).get();
        }
        else {
            throw new InnovationNotFoundException();
        }
    }



//    @Override
//    public List<Innovation> getAllInnovations() {
//        return innovationRepo.findAll();
//    }

    @Override
    public Map<String, Object> getAllInnovations(int page, int size, boolean recent) {
        Map<String, Object> response = new HashMap<>();
        Pageable paging;
        Page<Innovation> pageTuts;

        if(recent){
            paging = PageRequest.of(page, size, Sort.by("dateOfCreation").descending());
            pageTuts = innovationRepo.findAll(paging);
        }
        else{
            paging = PageRequest.of(page, size, Sort.by("dateOfCreation").ascending());
            pageTuts = innovationRepo.findAll(paging);
        }
        response.put("totalpages", pageTuts.getTotalPages());
        response.put("allInnovations", pageTuts.getContent());
        response.put("resultsCount", pageTuts.getTotalElements());
        return response;
    }


    @Override
    public Map<String, Object> getInnovationByFiler(String innovatorId,int page,int size,String domain){
        Map<String, Object> response = new HashMap<>();
        Pageable paging;
        Page<Innovation> pageTuts;
        if(domain.length()>0){
            paging = PageRequest.of(page, size, Sort.by("dateOfCreation").descending());
            pageTuts = innovationRepo.findByInnovatorIdAndDomain(innovatorId,domain,paging);
        }
        else {
            paging = PageRequest.of(page, size, Sort.by("dateOfCreation").descending());
            pageTuts = innovationRepo.findByInnovatorId(innovatorId,paging);
        }
        response.put("totalpages",pageTuts.getTotalPages());
        response.put("innovations",pageTuts.getContent());
        response.put("totalInnovations", pageTuts.getTotalElements());
        return response;
    }

    @Override
    public Map<String, Object> filterInnovationsByDomainAndStatus(String status, int page, int size, String[] domain, String search, boolean recent) {
        Map<String, Object> result = new HashMap<>();
        List<Innovation> innovationsWithAllDomains = new ArrayList<>();
        Pageable paging;
        Page<Innovation> pages;
        int numberOfPages=0;
        long resultsCount=0;
        if(recent && status.length()==0){
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").descending());
            for(String d : domain){
                d = d.substring(1,d.length()-1);
                System.out.println(d);
                System.out.println(d.length());
                pages = innovationRepo.findByDomain(d,paging);
                System.out.println("pages :"+pages);
                numberOfPages += pages.getTotalPages();
                resultsCount += pages.getTotalElements();
                innovationsWithAllDomains.addAll(pages.getContent());
                System.out.println("domains length"+domain.length);
                System.out.println("innovations list if "+innovationsWithAllDomains);
            }
        }
        else if(!recent && status.length()==0){
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").ascending());
            for(String d : domain){
                d = d.substring(1,d.length()-1);
                pages = innovationRepo.findByDomain(d,paging);
                numberOfPages += pages.getTotalPages();
                innovationsWithAllDomains.addAll(pages.getContent());
                resultsCount += pages.getTotalElements();
            }
        }
        else if(status.length()>0){

            if(domain.length>0){
                paging = PageRequest.of(page,size);
                pages = innovationRepo.findByStatus(status, paging);
                numberOfPages = pages.getTotalPages();
                innovationsWithAllDomains.addAll(pages.getContent());
                resultsCount+=pages.getTotalElements();
            }
            else{
                paging = PageRequest.of(page,size);
                for(String d : domain){
                    pages = innovationRepo.findByDomainAndStatus(d,status,paging);
                    numberOfPages += pages.getTotalPages();
                    innovationsWithAllDomains.addAll(pages.getContent());
                    System.out.println("innovations list else if"+innovationsWithAllDomains);
                    resultsCount += pages.getTotalElements();
                }
            }

        }
        else{
            paging = PageRequest.of(page,size);
            for(String d : domain){
                pages = innovationRepo.findByDomain(d,paging);
                numberOfPages += pages.getTotalPages();
                innovationsWithAllDomains.addAll(pages.getContent());
                System.out.println("innovations list else "+innovationsWithAllDomains);
                resultsCount += pages.getTotalElements();
            }
        }
        result.put("totalpages",numberOfPages);
        result.put("innovations",innovationsWithAllDomains);
        result.put("count",resultsCount);
        return result;
    }

    @Override
    public List<Innovation> searchInnovations(String query){
        if(query.length()==0){
            return new ArrayList<>();
        }
        List<Innovation> desc = innovationRepo.findByRegexpInnovationDesc(query);
        List<Innovation> total = innovationRepo.findByRegexpInnovationName(query);
        for(int j = 0; j<desc.size(); j++){
            if(!total.contains(desc.get(j))){
                total.add(desc.get(j));
            }
        }
        return total;
    }
}
