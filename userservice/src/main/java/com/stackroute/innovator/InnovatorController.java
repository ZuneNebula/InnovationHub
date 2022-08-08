package com.stackroute.innovator;

import com.stackroute.exception.InnovatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/v1/innovators"})
public class InnovatorController {
    @Autowired
    InnovatorService innovatorService;
    private ResponseEntity responseEntity;

    public InnovatorController() {
    }

//    @PostMapping({"/"})
//    public ResponseEntity<Innovator> createNewInnovator(@RequestBody Innovator innovator) throws InnovatorAlreadyExistsException {
//        try {
//            Innovator innovatorData = this.innovatorService.saveInnovator(innovator);
//            this.responseEntity = new ResponseEntity(innovatorData, HttpStatus.CREATED);
//        } catch (InnovatorAlreadyExistsException var3) {
//            throw new InnovatorAlreadyExistsException();
//        } catch (Exception var4) {
//            this.responseEntity = new ResponseEntity("error while adding innovator data", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return this.responseEntity;
//    }

    @GetMapping({"/"})
    public ResponseEntity<List<Innovator>> getAllInnovators() {
        try {
            List<Innovator> innovatorList = this.innovatorService.getInnovators();
            this.responseEntity = new ResponseEntity(innovatorList, HttpStatus.OK);
        } catch (Exception var2) {
            this.responseEntity = new ResponseEntity("error while fetching innovator data", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return this.responseEntity;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Innovator> getInnovatortById(@PathVariable("id") String id) throws InnovatorNotFoundException {
        try{
            Innovator innovator = this.innovatorService.getInnovatorById(id);
            this.responseEntity = new ResponseEntity(innovator, HttpStatus.OK);
        }
        catch (InnovatorNotFoundException e){
            throw new InnovatorNotFoundException();
        }
        catch (Exception ex){
            this.responseEntity = new ResponseEntity("error while fetching innovator data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return this.responseEntity;
    }

    @PutMapping({"/"})
    public ResponseEntity<Innovator> updateInnovator(@RequestBody Innovator innovator) throws InnovatorNotFoundException {
        try {
            Innovator innovatorData = innovatorService.updateInnovator(innovator);
            this.responseEntity = new ResponseEntity(innovatorData, HttpStatus.ACCEPTED);
        } catch (InnovatorNotFoundException var3) {
            throw new InnovatorNotFoundException();
        } catch (Exception var4) {
            this.responseEntity = new ResponseEntity("error while updating innovator data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return this.responseEntity;
    }
}

