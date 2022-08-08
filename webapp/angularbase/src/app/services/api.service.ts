import { Proposal } from './../proposal';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  // commonUrl="api/v1/proposals";

  httpOptions={
    header: new HttpHeaders({
      'Content-Type':'application/json'
    })
  }

  postProposal(proposal:Proposal):Observable<Proposal>{
    return this.httpClient.post<Proposal>("innmgt/api/v1/proposals", JSON.stringify(proposal),{'headers': {'Content-Type':'application/json'}})
  }

  getProposals():Observable<Proposal>{
    return this.httpClient.get<Proposal>("innmgt/api/v1/proposals/expert/");
  }

  getProposalById(id:string):Observable<Proposal>{
    return this.httpClient.get<Proposal>("innmgt/api/v1/proposals/"+id);
  }

  updateProposal(proposal:Proposal):Observable<Proposal>{
    return this.httpClient.put<Proposal>("innmgt/api/v1/proposals", proposal, {responseType:"json"});
  }

  deleteProposalById(id:string):Observable<Proposal>{
    return this.httpClient.delete<Proposal>("innmgt/api/v1/proposals/"+id);
  }

  getProposalsByDomain(domain:string):Observable<Proposal>{
    return this.httpClient.get<Proposal>("innmgt/api/v1/proposals/domain/"+domain);
  }

  getProposalsByFilter(status:string,page:number,size:number,recent:boolean){
    const params = new HttpParams()
        .set('status', status)
        .set('page', page)
        .set('size', size)
        .set("search","")
        .set("recent",recent);
        console.log(params);
        
    return this.httpClient.get<any>("innmgt/api/v1/proposals/filter",{params});
  }

  getProposalsByInnovationId(innovationId: string){
    return this.httpClient.get<Proposal[]>("innmgt/api/v1/proposals/innovationId/"+innovationId);
  }

  getProposalsBySearch(query:string){
    const params = new HttpParams()
        .set('query', query)
        console.log(params);
    return this.httpClient.get<any>("innmgt/api/v1/proposals/search",{params});
  }

  getProposalsByInnovation(innovationId: string,page: number,size: number){
    const params = new HttpParams()
    .set('innovationId',innovationId)
    .set('page', page)
    .set('size',size);
    return this.httpClient.get<any>("innmgt/api/v1/proposals/innovation",{params})
  }

  innovationId!:string;
  setInnovationId(data:string){
    this.innovationId=data;
  } 

  getInnovationId(){
    return this.innovationId;
  }

  proposalId!:string;

  setProposalId(data:string){
    this.proposalId=data;
  }

  getProposalId(){
    return this.proposalId;
  }
}
