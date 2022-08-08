package com.stackroute.rabbitMQ;

public class EventDataDTO {
    private String innovationId;
    private String proposalId;
    private String innovatorId;
    private String expertId;

    public EventDataDTO() {
    }

    public EventDataDTO(String innovationId, String proposalId, String innovatorId, String expertId) {
        this.innovationId = innovationId;
        this.proposalId = proposalId;
        this.innovatorId = innovatorId;
        this.expertId = expertId;
    }

    public String getInnovationId() {
        return innovationId;
    }

    public void setInnovationId(String innovationId) {
        this.innovationId = innovationId;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getInnovatorId() {
        return innovatorId;
    }

    public void setInnovatorId(String innovatorId) {
        this.innovatorId = innovatorId;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }
}
