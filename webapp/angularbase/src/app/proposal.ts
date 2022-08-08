import { PrivateComment } from './private-comment';
import { Attachment } from "./attach";

export interface Proposal {
    "proposalId":string,
    "expertId":string,
    "innovationId":string,
    "proposalTitle":string,
    "proposalDescription":string,
    "expertName":string,
    "statusOfProposal":string,
    "domain":string,
    "rating":number,
    "dateOfCreation":Date,
    "coverImage":Attachment,
    "attachment":Attachment[],
    "privateComments": PrivateComment[]
}