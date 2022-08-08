import { CookiesService } from 'src/app/services/cookies.service';
import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,private cookieservice: CookiesService, private router:Router) { }

  ngOnInit(){
    if(this.cookieservice.isExpert()){
      this.isExpert= true;
    }
    if(this.cookieservice.isInnovator()){
      this.isInnovator=true;
    }
    if(!this.cookieservice.getAuth){
      this.logout();
      this.router.navigateByUrl("/");
    }
  }

  logout() {
    this.cookieservice.isInnovatorLoggedIn = false;
    this.cookieservice.isExpertLoggedIn = false;
  }

  isInnovator=false;
  isExpert = false;

  test(){
    console.log("expert "+ this.cookieservice.isExpertLoggedIn);
    console.log("innovator "+ this.cookieservice.isInnovatorLoggedIn);
  }

  images=[
    {path:'../../assets/car1.webp'},
    {path:'../../assets/car2.webp'},
    {path:'../../assets/car3.jpg'},
    {path:'../../assets/car4.jpg'},
  ]

}
