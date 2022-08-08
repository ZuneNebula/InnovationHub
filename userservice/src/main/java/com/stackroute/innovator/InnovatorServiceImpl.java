package com.stackroute.innovator;

import com.stackroute.exception.InnovatorAlreadyExistsException;
import com.stackroute.exception.InnovatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InnovatorServiceImpl implements InnovatorService {
    @Autowired
    InnovatorRepository innovatorRepository;

    public InnovatorServiceImpl() {
    }

    @Override
    public Innovator saveInnovator(Innovator innovator) throws InnovatorAlreadyExistsException {
        if (this.innovatorRepository.findById(innovator.getUsername()).isPresent()) {
            throw new InnovatorAlreadyExistsException();
        } else {
            UUID id = UUID.randomUUID();

            System.out.println("uuid generator"+String.valueOf(id)+"raw id"+id);
            innovator.setInnovatorId(String.valueOf(id));
            return (Innovator)this.innovatorRepository.save(innovator);
        }
    }

    @Override
    public List<Innovator> getInnovators() {

        return this.innovatorRepository.findAll();
    }

    @Override
    public Optional<Innovator> getInnovatorByUsername(String username) throws InnovatorNotFoundException {
        if(!this.innovatorRepository.findById(username).isPresent()){
            throw new InnovatorNotFoundException();
        }
        else{
            return innovatorRepository.findById(username);
        }
    }

    @Override
    public Innovator updateInnovator(Innovator innovator) throws InnovatorNotFoundException {
        if(!this.innovatorRepository.findById(innovator.getUsername()).isPresent()){
            throw new InnovatorNotFoundException();
        }
        else{
            return innovatorRepository.save(innovator);
        }
    }

    @Override
    public Innovator getInnovatorById(String id) throws InnovatorNotFoundException{
        if(this.innovatorRepository.findInnovatorById(id)==null){
            throw new InnovatorNotFoundException();
        }
        else{
            return innovatorRepository.findInnovatorById(id);
        }
    }
}

