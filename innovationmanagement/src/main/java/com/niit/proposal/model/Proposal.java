package com.niit.proposal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Proposal {
    @Id
    private String proposalId;
    private String expertId;
    private String innovationId;
    private String proposalTitle;
    private String proposalDescription;
    private String expertName;
    private String statusOfProposal;
    private String domain;
    private float rating;
    private Date dateOfCreation;
    private AttachedFile coverImage;
    private AttachedFile[] attachment;
    private PrivateComments[] privateComments;

    public Proposal() {
    }

    public Proposal(String proposalId, String expertId, String innovationId, String proposalTitle, String proposalDescription, String expertName, String statusOfProposal, String domain, float rating, Date dateOfCreation, AttachedFile coverImage, AttachedFile[] attachment, PrivateComments[] privateComments) {
        this.proposalId = proposalId;
        this.expertId = expertId;
        this.innovationId = innovationId;
        this.proposalTitle = proposalTitle;
        this.proposalDescription = proposalDescription;
        this.expertName = expertName;
        this.statusOfProposal = statusOfProposal;
        this.domain = domain;
        this.rating = rating;
        this.dateOfCreation = dateOfCreation;
        this.coverImage = coverImage;
        this.attachment = attachment;
        this.privateComments = privateComments;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getProposalTitle() {
        return proposalTitle;
    }

    public void setProposalTitle(String proposalTitle) {
        this.proposalTitle = proposalTitle;
    }

    public String getProposalDescription() {
        return proposalDescription;
    }

    public void setProposalDescription(String proposalDescription) {
        this.proposalDescription = proposalDescription;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public AttachedFile getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(AttachedFile coverImage) {
        this.coverImage = coverImage;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getInnovationId() {
        return innovationId;
    }

    public void setInnovationId(String innovationId) {
        this.innovationId = innovationId;
    }

    public String getStatusOfProposal() {
        return statusOfProposal;
    }

    public void setStatusOfProposal(String statusOfProposal) {
        this.statusOfProposal = statusOfProposal;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public AttachedFile[] getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachedFile[] attachment) {
        this.attachment = attachment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public PrivateComments[] getPrivateComments() {
        return privateComments;
    }

    public void setPrivateComments(PrivateComments[] privateComments) {
        this.privateComments = privateComments;
    }
}
