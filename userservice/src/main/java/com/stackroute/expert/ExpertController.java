package com.stackroute.expert;

import com.stackroute.exception.ExpertNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/v1/experts"})
public class ExpertController {
    @Autowired
    ExpertService expertService;
    private ResponseEntity responseEntity;

    public ExpertController() {
    }

    //doesnt get called along with delete
//    @PostMapping({"/"})
//    public ResponseEntity<Expert> createNewExpert(@RequestBody Expert expert) throws ExpertAlreadyExistsException {
//        try {
//            Expert expertData = this.expertService.saveExpert(expert);
//            this.responseEntity = new ResponseEntity(expertData, HttpStatus.CREATED);
//        } catch (ExpertAlreadyExistsException var3) {
//            throw new ExpertAlreadyExistsException();
//        } catch (Exception var4) {
//            this.responseEntity = new ResponseEntity("error while adding expert data", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return this.responseEntity;
//    }


    @GetMapping({"/"})
    public ResponseEntity<List<Expert>> getAllExperts() {
        try {
            List<Expert> expertList = this.expertService.getExperts();
            this.responseEntity = new ResponseEntity(expertList, HttpStatus.OK);
        } catch (Exception var2) {
            this.responseEntity = new ResponseEntity("error while fetching experts data", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return this.responseEntity;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Expert> getExpertById(@PathVariable("id") String id) throws ExpertNotFoundException {
        try{
            Expert expert = this.expertService.getExpertById(id);
            this.responseEntity = new ResponseEntity(expert, HttpStatus.OK);
        }
        catch (ExpertNotFoundException e){
            throw new ExpertNotFoundException();
        }
        catch (Exception ex){
            this.responseEntity = new ResponseEntity("error while fetching expert data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return this.responseEntity;
    }

    @PutMapping({"/"})
    public ResponseEntity<Expert> updateExpert(@RequestBody Expert expert) throws ExpertNotFoundException {
        try {
            Expert expertData = this.expertService.updateExpert(expert);
            this.responseEntity = new ResponseEntity(expertData, HttpStatus.ACCEPTED);
        } catch (ExpertNotFoundException var3) {
            throw new ExpertNotFoundException();
        } catch (Exception var4) {
            this.responseEntity = new ResponseEntity("error while updating expert data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return this.responseEntity;
    }
}
