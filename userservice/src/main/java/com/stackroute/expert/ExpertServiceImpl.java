package com.stackroute.expert;


import com.stackroute.exception.ExpertAlreadyExistsException;
import com.stackroute.exception.ExpertNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    ExpertRepository expertRepository;

    public ExpertServiceImpl() {
    }

    @Override
    public Expert saveExpert(Expert expert) throws ExpertAlreadyExistsException {
        if (this.expertRepository.findById(expert.getUsername()).isPresent()) {
            throw new ExpertAlreadyExistsException();
        } else {
            UUID id = UUID.randomUUID();
            expert.setExpertId(String.valueOf(id));
            //by default specialization would be Any
            String[] temp = new String[]{"Any"};
            expert.setSpecialization(temp);
            return (Expert)this.expertRepository.save(expert);
        }
    }

    @Override
    public List<Expert> getExperts() {
        return this.expertRepository.findAll();
    }

    @Override
    public Optional<Expert> getExpertByUsername(String username) throws ExpertNotFoundException{
        if(!this.expertRepository.findById(username).isPresent()){
            throw new ExpertNotFoundException();
        }
        else{
            return expertRepository.findById(username);
        }
    }

    @Override
    public Expert updateExpert(Expert expert) throws ExpertNotFoundException{
        if(!this.expertRepository.findById(expert.getUsername()).isPresent()){
            throw new ExpertNotFoundException();
        }
        else{
            return expertRepository.save(expert);
        }
    }

    @Override
    public Expert getExpertById(String id) throws ExpertNotFoundException{
        if(this.expertRepository.findExpertById(id)==null){
            throw new ExpertNotFoundException();
        }
        else{
            return expertRepository.findExpertById(id);
        }
    }

}
