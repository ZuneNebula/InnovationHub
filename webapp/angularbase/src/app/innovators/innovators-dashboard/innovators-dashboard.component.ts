import { CookiesService } from 'src/app/services/cookies.service';
import { Component, Input } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Innovator } from 'src/app/innovator';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ServerService } from 'src/app/services/server.service';
import { Innovation } from 'src/app/innovation';
import { error } from '@angular/compiler/src/util';
import { Notification } from 'src/app/notification';
import { ApiService } from 'src/app/services/api.service';
import * as async from "async";

@Component({
  selector: 'app-innovators-dashboard',
  templateUrl: './innovators-dashboard.component.html',
  styleUrls: ['./innovators-dashboard.component.css']
})
export class InnovatorsDashboardComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );


  constructor(private breakpointObserver: BreakpointObserver, private cookies: CookiesService,
    private router: Router, private modalService: NgbModal, private serverService: ServerService, private apiService: ApiService) { }

  cookieValue!: Innovator; //value you get from the cookie

  logout() {
    this.cookies.isInnovatorLoggedIn = false;
  }

  openProfile() {
    this.router.navigateByUrl("innovators/profile");
  }

  innovatorValue: Innovator = { //value you see on the form
    innovatorId: '',
    username: '',
    firstName: '',
    lastName: '',
    avatarUrl: '',
    email: '',
    occupation: '',
    updatedBy: '',
    updatedOn: new Date,
    tags: []
  }
  expertNames: string[] = [];
  proposalNames: string[] = [];
  innovationNames: string[] = [];
  notificationObjArray: any[] = [];
  notificationMessage: string[] = [];

  allInnovations: Innovation[] = [];
  testing!: string;

  ngOnInit() {
    if (!this.cookies.getAuth) {
      this.logout();
      this.router.navigateByUrl("/");
    }
    this.cookieValue = (this.cookies.login());
    this.innovatorValue = this.cookieValue;
    // this.getNotifications();
    this.getNotificationsData();
    // this.testingInnoavtion();
  }

  ngOnChanges(): void {
    if (!this.cookies.getAuth) {
      this.logout();
      this.router.navigateByUrl("/");
    }
    this.serverService.getInnovator(this.cookieValue.innovatorId).subscribe(data => {
      this.innovatorValue = data;
    });
    this.getNotificationsData();
  }

  getNotificationsData() {
    this.serverService.getProposalNotification(this.cookieValue.innovatorId).subscribe(data => {
      console.log("innovator notifications");
      console.log(data);
      async.map(data, (notfdata: any, next: CallableFunction) => {

        async.waterfall([
          (cb: CallableFunction) => {
            this.serverService.getExpert(notfdata.sourceId).subscribe(data => {
              cb(null, data);
            })
          },
          (expertData: any, cb: CallableFunction) => {
            this.serverService.getInnovationById(notfdata.innovationId).subscribe(data => {
              cb(null, expertData, data);
            })
          },
          (expertData: any, innovatorData:any, cb: CallableFunction) => {
            this.apiService.getProposalById(notfdata.proposalId).subscribe(data => {
              cb(null, {expertData, innovatorData, proposalData: data});
            })
          },
        ], (err: any, {expertData, innovatorData, proposalData }:any) => {
          if (err) {
            next(err)
          } else {
            const notfObj = {
              expDate: notfdata.date,
              expFirstName: expertData.firstName,
              expInnoName: innovatorData.innovationName,
              expProposalTitle: proposalData.proposalTitle,
              expProposalId: proposalData.proposalId,
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

  // async getNotifications() {
  //   console.log("inside noti");
  //   await this.serverService.getProposalNotification(this.cookieValue.innovatorId).subscribe(data => {
  //     //console.log(data);
  //     data.forEach((value: any, i) => {
  //       //this.notifications.push(i.notificationData.message);
  //       this.fetchNotiData(value.sourceId, value.innovationId, value.proposalId).then(() => {

  //         console.log("exp Names");
  //         console.log(this.expertNames);
  //         console.log("innovation names");
  //         console.log(this.innovationNames);
  //         console.log("proposal names");
  //         console.log(this.proposalNames);
  //         //this.notificationMessage.push(this.expertNames[i]+ " has sent you a proposal "+ this.proposalNames[i]+ " for innovation "+ this.innovationNames[i]);
  //         console.log(this.notificationObjArray);
  //         this.notificationMessage.push(this.notificationObjArray[i]['expFirstName'] + " has sent you a proposal " + this.notificationObjArray[i]['expProposalTitle'] + " for innovation " + this.notificationObjArray[i]['expInnoName']);
  //         console.log(this.notificationMessage);

  //       });

  //     })
  //   })
  // }

  // async fetchNotiData(expertId: string, innovationId: string, proposalId: string) {
  //   let notiificationObj: any = {};
  //   await this.serverService.getExpert(expertId).subscribe(data => {
  //     this.expertNames.push(data.firstName);
  //     setTimeout(() => {
  //       notiificationObj['expFirstName'] = data.firstName;
  //       console.log("done");
  //     }, 3000);
  //   })
  //   await this.serverService.getInnovationById(innovationId).subscribe(data => {
  //     this.innovationNames.push(data.innovationName);

  //     setTimeout(() => {
  //       notiificationObj['expInnoName'] = data.innovationName;
  //       console.log("done");
  //     }, 3000);
  //   })
  //   await this.apiService.getProposalById(proposalId).subscribe(data => {
  //     this.proposalNames.push(data.proposalTitle);

  //     setTimeout(() => {
  //       notiificationObj['expProposalTitle'] = data.proposalTitle;
  //       console.log("done");
  //     }, 3000);
  //   })

  //   this.notificationObjArray.push(notiificationObj);
  // }

  testingInnoavtion() {
    // let testings = this.serverService.testInnovations("Hi",123).subscribe((data:string)=> {
    //   console.log("testingInnoavtion"+data);
    //   this.testing = data;
    // })
    // console.log(testings);
  }

}
