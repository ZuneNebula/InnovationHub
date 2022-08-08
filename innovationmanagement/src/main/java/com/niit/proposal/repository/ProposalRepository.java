package com.niit.proposal.repository;

import com.niit.proposal.model.Proposal;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Configuration
public interface ProposalRepository extends MongoRepository<Proposal, String> {
    Page<Proposal> findByExpertId(String expertId, Pageable pageable);
    List<Proposal> findByInnovationId(String innovationId);

    Page<Proposal> findByExpertIdAndStatusOfProposal(String expertId, String statusOfProposal, Pageable pageable);

    @Query("{ 'proposalTitle' : { $regex: ?0 } }")
    List<Proposal> findByRegexProposalTitle(String regexp);

    @Query("{ 'proposalDescription' : { $regex: ?0 } }")
    List<Proposal> findByRegexProposalDescription(String regexp);

    //for innovators view
    Page<Proposal> findByInnovationId(String innovationId, Pageable pageable);
}
