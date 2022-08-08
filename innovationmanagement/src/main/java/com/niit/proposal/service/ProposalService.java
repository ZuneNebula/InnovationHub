package com.niit.proposal.service;

import com.niit.proposal.model.Proposal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProposalService {

    Proposal saveProposal(Proposal proposal) throws IOException;
    List<Proposal> getAllProposals(String expertId);
    Proposal getProposalById(String proposalId);
    boolean deleteProposal(String proposalId);
    Proposal updateProposal(Proposal proposal);
    List<Proposal> getProposalsByDomain(String domain);
    List<Proposal> getProposalsByInnovationId(String innovationId);

    //pagination, search and filter
    Map<String, Object> filterProposalsOnIdStatus(String expertId, int page, int size, String status, String search, boolean recent);

    List<Proposal> searchProposals(String query);
    Map<String, Object> getProposalsByInnovation(String innovationId,int page,int size);

//    public Map<String, Object> getProposalsBasedOnStatus(String expertId, String status, int page, int size);

}
