package com.niit.proposal.controller;

import com.niit.cookieConfig.CookieConfig;
import com.niit.proposal.model.Proposal;
import com.niit.proposal.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;
    private ResponseEntity responseEntity;

    @Autowired
    CookieConfig cookieConfig;

    @PostMapping()
    public ResponseEntity<?> saveProposal(@RequestBody Proposal proposal) throws IOException {
        System.out.println("inside proposal controller save method");
        try{
            proposalService.saveProposal(proposal);
            return new ResponseEntity(proposal, HttpStatus.CREATED);

        }
        catch (Exception ex){
            return new ResponseEntity("error while adding proposal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expert")
    public List<Proposal> getProposalsForExpert(@CookieValue("JWT-TOKEN") Cookie cookie){
        System.out.println("inside controller");
//        System.out.println("expert id controller: "+expertId);
//        try{
        List<Proposal> expertProposals=proposalService.getAllProposals(cookieConfig.getExpertIdFromCookie(cookie));
        return expertProposals;
//            responseEntity = new ResponseEntity(, HttpStatus.OK);
//        }
//        catch (Exception ex){
//            responseEntity = new ResponseEntity("error while fetching proposal list", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        System.out.println("array from controller");
//        System.out.println(proposalService.getAllProposals(expertId));
//        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProposalById(@PathVariable("id") String proposalId){
        try{
            return new ResponseEntity(proposalService.getProposalById(proposalId),HttpStatus.OK);
        }
        catch (Exception ex){
            return  new ResponseEntity("error while fetching proposal with id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateProposal(@RequestBody Proposal proposal){
        try{
            Proposal proposalObj = proposalService.updateProposal(proposal);
            return new ResponseEntity(proposalObj, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity("error while updating", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProposalById(@PathVariable("id") String id){
        try{
            return new ResponseEntity(proposalService.deleteProposal(id), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity("error while deleting proposal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //for displaying proposals in innovator view
    @GetMapping("/domain/{domain}")
    public ResponseEntity<?> getProposalsByDomain(@PathVariable("domain") String domain){
        try{
            return new ResponseEntity(proposalService.getProposalsByDomain(domain), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity("error while fetching list with domain", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/filter")
    public Map<String, Object> proposalsInPages(@RequestParam int page, @RequestParam int size, @RequestParam String status,@RequestParam boolean recent, @CookieValue("JWT-TOKEN")Cookie cookie){
        return proposalService.filterProposalsOnIdStatus(cookieConfig.getExpertIdFromCookie(cookie),page,size,status,"",recent);
    }

    @GetMapping("/innovationId/{id}")
    public List<Proposal> getProposalsByInnovationId(@PathVariable String id){
        return proposalService.getProposalsByInnovationId(id);
    }

    @GetMapping("/search")
    public List<Proposal> searchProposals(@RequestParam("query") String query){
        List<Proposal> allProposals = proposalService.searchProposals(query);
        return allProposals;
    }

    @GetMapping("/innovation")
    public Map<String, Object> getProposalsByInnovation(@RequestParam String innovationId,
                                                        @RequestParam int page, @RequestParam int size){
        return proposalService.getProposalsByInnovation(innovationId,page,size);
    }

//    @GetMapping("/pages")
//    public Map<String, Object> allProposalsInPages(@RequestParam String expertId,
//                                                     @RequestParam int page,
//                                                     @RequestParam int size){
//        System.out.println("******** Inside getting all the proposals in pages-Controller ********");
//        return proposalService.getProposalsInpages(expertId,page,size);
//    }

}
