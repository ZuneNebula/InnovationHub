import { Innovator } from './../innovator';
import { CookiesService } from '../services/cookies.service';
import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { ServerService } from '../services/server.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';
import { map, Observable, shareReplay } from 'rxjs';
import { Innovation } from '../innovation';

@Component({
  selector: 'app-innovators',
  templateUrl: './innovators.component.html',
  styleUrls: ['./innovators.component.css']
})
export class InnovatorsComponent {

//   isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
//   .pipe(
//     map(result => result.matches),
//     shareReplay()
//   );

constructor(private breakpointObserver: BreakpointObserver,private cookies: CookiesService,
  private router: Router, private modalService: NgbModal, private serverService: ServerService, 
  private route: ActivatedRoute) {}

// cookieValue!: Innovator; //value you get from the cookie

// dashboard(){
//   this.router.navigate([""])
// }

// logout(){
//   this.cookies.isInnovatorLoggedIn = false;
// }

// openProfile(){
//   this.router.navigateByUrl("innovators/profile");
// }

// innovatorValue: Innovator={ //value you see on the form
//   innovatorId:'',
//   username: '',
//   firstName: '',
//   lastName:'',
//   avatarUrl: '',
//   email: '',
//   occupation: '',
//   updatedBy: '',
//   updatedOn: new Date,
//   tags:[]
// }

// allInnovations: Innovation[] = [];
// testing!: string;

// ngOnInit(): void {
//   this.cookies.isInnovatorLoggedIn = true;
//   this.cookieValue = (this.cookies.login());
//   this.innovatorValue = this.cookieValue;
//   this.testingInnoavtion();
// }

// ngOnChanges(): void {
//   this.serverService.getInnovator(this.cookieValue.innovatorId).subscribe(data=>{
//     this.innovatorValue = data;
//   })
// }

// testingInnoavtion(){
//   let testings = this.serverService.testInnovations("Hi",123).subscribe((data:string)=> {
//     console.log("testingInnoavtion"+data);
//     this.testing = data;
//   })
//   console.log(testings);
// }



}
