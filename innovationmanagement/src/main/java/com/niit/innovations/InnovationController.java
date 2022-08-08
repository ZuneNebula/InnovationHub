package com.niit.innovations;

import com.niit.cookieConfig.CookieConfig;
import com.niit.exception.InnovationAlreadyExistsException;
import com.niit.exception.InnovationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;

@RestController
@RequestMapping({"api/v1/innovations"})
public class InnovationController {
    @Autowired
    public InnovationService innovationService;
    public ResponseEntity responseEntity;

    @Autowired
    CookieConfig cookieConfig;

    //for saving new Innovation
    @PostMapping()
    public ResponseEntity<?> saveInnovation(@RequestBody Innovation innovation) throws InnovationAlreadyExistsException, IOException {
        try {
            responseEntity = new ResponseEntity(innovationService.saveInnovation(innovation), HttpStatus.CREATED);
        }
        catch (InnovationAlreadyExistsException | IOException ex){
            responseEntity = new ResponseEntity(innovationService.saveInnovation(innovation), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //for getting all innovations
//    @GetMapping("/all")
//    public ResponseEntity<?> getAllInnovations(){
//        try{
//            responseEntity=new ResponseEntity(innovationService.getAllInnovations(),HttpStatus.OK);
//        }
//        catch (Exception ex){
//            responseEntity=new ResponseEntity("error loading all innovations",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
    @GetMapping("/all")
    public Map<String, Object> allInnovationsInPlatform(@RequestParam int page, @RequestParam int size, @RequestParam boolean recent){
        return innovationService.getAllInnovations(page,size,recent);
    }

    //for filtering innovation based on domain in Innovator view and
    //also getting innovations based on innovator ID
    @GetMapping({"/filter"})
    public Map<String, Object> allInnovationsInPages(@RequestParam int page, @RequestParam int size,
                                                     @RequestParam String domain,
                                                     @CookieValue("JWT-TOKEN") Cookie cookie) throws InnovationNotFoundException {
        return innovationService.getInnovationByFiler(cookieConfig.getInnovatorIdFromCookie(cookie),page,size,domain);
    }

    //used for experts view
    @GetMapping({"/filterForExperts"})
    public Map<String,Object> innovationsFilteredWithPaging(@RequestParam int page, @RequestParam int size, @RequestParam String status, @RequestParam boolean recent, @RequestParam String domains) throws InnovationNotFoundException{

        System.out.println("domain string"+domains);
        domains = domains.substring(1,domains.length()-1);

        String[] domainArray = domains.split(",");
        return innovationService.filterInnovationsByDomainAndStatus(status,page,size,domainArray,"",recent);
    }

    //for getting innovation based on innovationId
    @GetMapping({"id/{innovationId}"})
    public Innovation innovationById(@PathVariable String innovationId) throws InnovationNotFoundException {
       try {
           return innovationService.getInnovationById(innovationId);
       } catch (InnovationNotFoundException e) {
           throw new InnovationNotFoundException();
       }
    }

    //for deleting innovation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInnovation(@PathVariable String id) throws InnovationNotFoundException {
        try {
            innovationService.deleteInnovation(id);
            responseEntity = new ResponseEntity("Deleted Successfully",HttpStatus.OK);
        }
        catch (InnovationNotFoundException ex){
            responseEntity = new ResponseEntity("Innovation not found",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //for updating innovation
    @PutMapping()
    public ResponseEntity<?> updateInnovation(@RequestBody Innovation innovation) throws InnovationNotFoundException{
        try {
            responseEntity = new ResponseEntity(innovationService.updateInnovation(innovation), HttpStatus.OK);
        }
        catch (InnovationNotFoundException ex){
            responseEntity = new ResponseEntity(innovationService.updateInnovation(innovation), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //for searching innovations
    @GetMapping("/search")
    public List<Innovation> searchInnovations(@RequestParam("query") String query){
        List<Innovation> allInnovations = innovationService.searchInnovations(query);
        return allInnovations;
    }
}
