import { Expert } from './../../expert';
import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Innovator } from 'src/app/innovator';
import { CookiesService } from 'src/app/services/cookies.service';
import { Router } from '@angular/router';
import { ServerService } from 'src/app/services/server.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from 'src/app/services/api.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';
import { Proposal } from 'src/app/proposal';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import * as async from "async";

@Component({
  selector: 'app-experts-dashboard',
  templateUrl: './experts-dashboard.component.html',
  styleUrls: ['./experts-dashboard.component.css']
})
export class ExpertsDashboardComponent implements OnInit{

  domains:any=['health', 'technology', 'environment','society'];

  proposalsByDomain:any=[];

  filterApplied:boolean=false;

  selectDomain(domain:string){
    this.filterApplied=true;
    const getByDomainObservable = this.apiService.getProposalsByDomain(domain);
    getByDomainObservable.subscribe((data:{})=>{
      this.proposalsByDomain=data;
    })
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
    
  
    logout(){
      this.cookies.isExpertLoggedIn = false;
    }
    
    openProfile() {
      this.router.navigateByUrl("/experts/profile");
    }
    constructor(private breakpointObserver: BreakpointObserver, private cookies: CookiesService,private router: Router, 
      private serverService:ServerService, private modalService:NgbModal, private apiService: ApiService, private dialog:MatDialog,
      private snackbar: MatSnackBar) {}

    cookieValue!: Expert; //value from cookie

    proposals:any=[];

    innovatorNames:string[]=[];
    proposalNames:string[]=[];
    innovationNames:string[]=[];
    notificationMessage:string[]=[];
    notificationObjArray:any[]=[];
    status:string[]=[];
  


    ngOnInit(): void {
      // const getObservable = this.apiService.getProposals();
      // getObservable.subscribe((data:{})=>{
      //   this.proposals=data;
      // });
      if(!this.cookies.getAuth){
        this.logout();
        this.router.navigateByUrl("/");
      }
      this.cookieValue = (this.cookies.login());
      this.serverService.getExpert(this.cookieValue.expertId).subscribe(data=>{
        if(data.specialization.length==1 && data.specialization.includes("Any")){
          this.openSnackBar();
        }
      })
      this.getNotificationsData();
    }

    ngOnChanges(){
      if(!this.cookies.getAuth){
        this.logout();
        this.router.navigateByUrl("/");
      }
      this.getNotificationsData();
    }
  
  
    viewProposalById(id:number){
      this.dialog.open(DialogComponent, { data: { proposalId:id } });
    }

    proposalToBeDeleted!:Proposal;

    delete(id:string){
      const deleteObservable = this.apiService.deleteProposalById(id);
      deleteObservable.subscribe((data)=>{
        this.proposalToBeDeleted=data;
      })
      window.location.reload();
    }

    //notifications
    getNotificationsData() {
      this.serverService.getProposalStatusNotification(this.cookieValue.expertId).subscribe(data => {
        async.map(data, (notfdata: any, next: CallableFunction) => {
          console.log("notf data");
          console.log(notfdata.notificationData.subject);
          async.waterfall([
            (cb: CallableFunction) => {
              this.serverService.getInnovator(notfdata.sourceId).subscribe(data => {
                let status=notfdata.notificationData.subject;
                cb(null, status, data);
              })
            },
            (statusData: any, expertData: any, cb: CallableFunction) => {
              this.serverService.getInnovationById(notfdata.innovationId).subscribe(data => {
                cb(null, statusData, expertData, data);
              })
            },
            (statusData:any, expertData: any, innovatorData:any, cb: CallableFunction) => {
              this.apiService.getProposalById(notfdata.proposalId).subscribe(data => {
                cb(null, {statusData, expertData, innovatorData, proposalData: data});
              })
            },
          ], (err: any, {statusData, expertData, innovatorData, proposalData }:any) => {
            if (err) {
              next(err)
            } else {
              const notfObj = {
                innoDate: notfdata.date,
                innoFirstName: expertData.firstName,
                innoInnoName: innovatorData.innovationName,
                innoProposalTitle: proposalData.proposalTitle,
                innoInnovationId: proposalData.innovationId,
                status: statusData,
                notif:notfdata
              }
              next(null, notfObj)
            }
          })
        }, (err:any, notificationList: any) => {
          if (err) {
            console.log("Error", err);
          } else {
            this.notificationObjArray = [...notificationList];
          }
        });
      })
    }
  
    markRead(notification:any){
      notification.status = "Read";
      this.serverService.updateNotifications(notification).subscribe(data=>{
        this.notificationObjArray=[];
        this.getNotificationsData();
      });
    }
  

    //snackbar
    horizontalPosition: MatSnackBarHorizontalPosition = 'right';
    verticalPosition: MatSnackBarVerticalPosition = 'bottom';

    openSnackBar() {
      this.snackbar.open('Your specialization is set to "Any" by default. Please change your profile to filter innovations by domain',
       'Close', {
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
    }
    landingPage(){
      this.router.navigateByUrl("/experts");
    }

}
