import { Innovation } from 'src/app/innovation';
import { HttpClient, HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Innovator } from '../innovator';
import { Expert } from '../expert';
import { CookiesService } from './cookies.service';
import { BehaviorSubject, Observable, Subject } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ServerService {

  constructor(private httpclient: HttpClient) { }

  updateInnovator(innovator:Innovator){
    return this.httpclient.put("userservice/api/v1/innovators/", innovator);
  }
  getInnovator(innovatorId: string){
    return this.httpclient.get<Innovator>("userservice/api/v1/innovators/"+innovatorId);
  }
  updateExpert(expert:Expert){
    return this.httpclient.put("userservice/api/v1/experts/", expert);
  }
  getExpert(expertId: string){
    return this.httpclient.get<Expert>("userservice/api/v1/experts/"+expertId);
  }


  //API FOR NOTIFICATIONS

  getProposalNotification(innovatorId:string){    
    return this.httpclient.get<Notification[]>("ntfservice/api/v1/notifications/"+innovatorId);
  }

  getProposalStatusNotification(expertId:string){    
    return this.httpclient.get<Notification[]>("ntfservice/api/v1/notifications/"+expertId);
  }

  updateNotifications(notification:Notification){
    return this.httpclient.put("ntfservice/api/v1/notifications/", notification);
  }


  //API FOR INNOVATIONS

  //for getting, posting, updating and deleting INNOVATIONS
  addInnovation(innovation: Innovation){
    return this.httpclient.post<Innovation>("innmgt/api/v1/innovations", JSON.stringify(innovation), {'headers':{'Content-Type':'application/json'}})
  }
  // getInnovations(){
  //   return this.httpclient.get<Innovation[]>("innmgt/api/v1/innovations");
  // }
  // getInnovationsOnscroll( page: number, size: number){
  //   const params = new HttpParams()
  //       .set("domain","")
  //       .set('page', page)
  //       .set('size', size)
  //       .set("search","");
  //       console.log("Inside service N scroll");
        
  //   return this.httpclient.get<any>("innmgt/api/v1/innovations/pages",{params});
  // }
  deleteInnovation(innovationId: string){
    return this.httpclient.delete<Innovation>("innmgt/api/v1/innovations/"+innovationId);
  }
  getInnovationById(innovationId: string){
    return this.httpclient.get<Innovation>("innmgt/api/v1/innovations/id/"+innovationId)
  }
  updateInnovation(innovation:Innovation){
    return this.httpclient.put<Innovation>("innmgt/api/v1/innovations",innovation);
  }
  // allInnovations():Observable<Innovation>{
  //   return this.httpclient.get<Innovation>("innmgt/api/v1/innovations/all");
  // }

  allInnovations(page:number, size:number, recent: boolean){
    const params = new HttpParams()
      .set('page',page)
      .set('size',size)
      .set('recent',recent);
    return this.httpclient.get<any>("innmgt/api/v1/innovations/all", {params});
  }

  public bSubjectList = new BehaviorSubject<any>(0);

  getInnovationsByFilter(domain: string, page: number, size: number,recent:boolean){
    const params = new HttpParams()
        .set('domain', domain)
        .set('page', page)
        .set('size', size);
        this.httpclient.get<any>("innmgt/api/v1/innovations/filter",{params}).subscribe(data => {
          // console.log("Inside behariuor subject: "+ JSON.stringify(data.allInnovation.length));
          this.bSubjectList.next(data);
        })
    return this.httpclient.get<any>("innmgt/api/v1/innovations/filter",{params});
  }

  getInnovationsBySearch(query:string){
    const params = new HttpParams()
        .set('query', query)
        console.log(params);
    return this.httpclient.get<any>("innmgt/api/v1/innovations/search",{params});
  }
  
  filterInnovationsForExpertsView(status: string, page: number, size: number, recent: boolean,domains:string[]){
    
    const params = new HttpParams()
      .set('status', status)
      .set('page', page)
      .set('size', size)
      .set("search", "")
      .set("recent", recent)
      .set("domains",JSON.stringify(domains));
    
    return this.httpclient.get<any>("innmgt/api/v1/innovations/filterForExperts",{params:params});    
  }

  innovationIdsetting!:string;
  setInnovationId(id:string){
    this.innovationIdsetting=id;
  }

  getInnovationId(){
    return this.innovationIdsetting;
  }

  


}
