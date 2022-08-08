package com.niit.proposal.service;

import com.niit.innovations.Innovation;
import com.niit.innovations.InnovationRepo;
import com.niit.proposal.model.Proposal;
import com.niit.proposal.repository.ProposalRepository;
import com.niit.rabbitMQ.EventDTO;
import com.niit.rabbitMQ.EventDataDTO;
import com.niit.rabbitMQ.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProposalServiceImpl implements ProposalService{

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private InnovationRepo repo;

    @Autowired
    private Producer producer;

    @Override
    public Proposal saveProposal(Proposal proposal) throws IOException {
        UUID id = UUID.randomUUID();
        proposal.setProposalId(String.valueOf(id));
        Proposal proposal1= repository.save(proposal);
        //producer stuff
        EventDTO eventDTO= new EventDTO();
        eventDTO.setEvent("NewProposalQueue");
        EventDataDTO eventDataDTO= new EventDataDTO();
        eventDataDTO.setProposalId(proposal1.getProposalId());
        eventDataDTO.setExpertId(proposal1.getExpertId());
        eventDataDTO.setInnovationId(proposal1.getInnovationId());
        Optional<Innovation> inno = repo.findById(proposal1.getInnovationId());
        System.out.println("innovator name "+ inno.get().getName());
        eventDataDTO.setInnovatorId(inno.get().getInnovatorId());
        eventDTO.setEventDataDTO(eventDataDTO);
        eventDTO.setEvent_ts(new Date());
        producer.sendProposalToRabbitMq(eventDTO);

        return proposal1;
    }

    @Override
    public List<Proposal> getAllProposals(String expertId) {
//        return repository.findAll();
        System.out.println("expert id service impl: "+expertId);
        List<Proposal> allProposals=repository.findAll();
        for(Proposal proposal:allProposals){
            System.out.println("Proposal list objects(all proposals)");
            System.out.println(proposal.getExpertId());
        }
        List<Proposal> proposalsOfExpert=new ArrayList<>();
        for(Proposal proposal:allProposals){
            if(proposal.getExpertId().equalsIgnoreCase(expertId)){
                System.out.println("expert id matches");
                proposalsOfExpert.add(proposal);
            }
        }
        System.out.println("list of proposals from service impl"+proposalsOfExpert);
        return proposalsOfExpert;
    }

    @Override
    public Proposal getProposalById(String proposalId) {
        return repository.findById(proposalId).get();
    }

    @Override
    public boolean deleteProposal(String proposalId) {
        repository.deleteById(proposalId);
        return true;
    }



//        return true;
    public Proposal updateProposal(Proposal proposal) {
        String status1 = repository.findById(proposal.getProposalId()).get().getStatusOfProposal();
        String status2 = proposal.getStatusOfProposal();
        if(!status1.equals(status2)){
            //producer stuff
            EventDTO eventDTO= new EventDTO();
            EventDataDTO eventDataDTO= new EventDataDTO();
            eventDataDTO.setProposalId(proposal.getProposalId());
            eventDataDTO.setExpertId(proposal.getExpertId());
            eventDataDTO.setInnovationId(proposal.getInnovationId());
            Optional<Innovation> inno = repo.findById(proposal.getInnovationId());
            System.out.println("innovator name "+ inno.get().getName());
            eventDataDTO.setInnovatorId(inno.get().getInnovatorId());
            eventDTO.setEventDataDTO(eventDataDTO);
            eventDTO.setEvent_ts(new Date());
            if(status2.equals("accepted")){
                eventDTO.setEvent("AcceptedProposalQueue");
                producer.sendAcceptedProposalToRabbitMq(eventDTO);
            }
            else{
                eventDTO.setEvent("RejectedProposalQueue");
                producer.sendRejectedProposalToRabbitMq(eventDTO);
            }

        }
        Proposal updatedProposal = repository.save(proposal);
        System.out.println("updated PRoposal: "+updatedProposal.toString());
        if (updatedProposal != null) {
            return updatedProposal;

        }

        return null;

    }

    @Override
    public List<Proposal> getProposalsByDomain(String domain) {
        List<Proposal> allProposals = repository.findAll();
        List<Proposal> proposalsWithDomain = new ArrayList<Proposal>();
        for(Proposal proposal: allProposals){
            if(proposal.getDomain().equals(domain)){
                proposalsWithDomain.add(proposal);
            }
        }
        return proposalsWithDomain;
    }

    @Override
    public Map<String, Object> filterProposalsOnIdStatus(String expertId, int page, int size, String statusOfProposal, String search, boolean recent) {
        Map<String, Object> result = new HashMap<>();
        Pageable paging;
        Page<Proposal> pageTuts;
        if(recent==true && statusOfProposal.length()==0){
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").descending());
            pageTuts = repository.findByExpertId(expertId,paging);
            System.out.println("inside if");
        }
        else if(!recent && statusOfProposal.length()==0){
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").ascending());
            pageTuts = repository.findByExpertId(expertId,paging);
            System.out.println("inside else if 1");
        }
        else if(recent && statusOfProposal.length()>0){
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").descending());
            pageTuts = repository.findByExpertIdAndStatusOfProposal(expertId,statusOfProposal,paging);
            System.out.println("status of proposal : "+statusOfProposal);
        }
        else if(!recent && statusOfProposal.length()>0){
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").ascending());
            pageTuts = repository.findByExpertIdAndStatusOfProposal(expertId,statusOfProposal,paging);
            System.out.println("inside else if 3");
        }
        else{
            paging = PageRequest.of(page,size,Sort.by("dateOfCreation").descending());
            pageTuts = repository.findByExpertId(expertId,paging);
            System.out.println("inside else");
        }
        result.put("totalpages",pageTuts.getTotalPages());
        result.put("allProposals",pageTuts.getContent());
        result.put("totalProposals",pageTuts.getTotalElements());
        System.out.println("result value in service: "+result);
        return result;
    }

    @Override
    public List<Proposal> searchProposals(String query) {
        if(query.length()==0){
            return new ArrayList<>();
        }
        List<Proposal> desc = repository.findByRegexProposalDescription(query);
        List<Proposal> total = repository.findByRegexProposalTitle(query);
        for(int j = 0; j<desc.size(); j++){
            if(!total.contains(desc.get(j))){
                total.add(desc.get(j));
            }

        }
        return total;
    }

    @Override
    public List<Proposal> getProposalsByInnovationId(String innovationId){
        List<Proposal> proposalList = repository.findByInnovationId(innovationId);
        return proposalList;
    }

    @Override
    public Map<String, Object> getProposalsByInnovation(String innovationId,int page,int size){
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page,size,Sort.by("dateOfCreation").descending());
        Page<Proposal> pageTuts = repository.findByInnovationId(innovationId, pageable);
        response.put("proposals", pageTuts.getContent());
        response.put("totalPages", pageTuts.getTotalPages());
        response.put("totalProposals", pageTuts.getTotalElements());
        return response;
    }
}
