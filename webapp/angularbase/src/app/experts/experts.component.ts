import { CookiesService } from '../services/cookies.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-experts',
  templateUrl: './experts.component.html',
  styleUrls: ['./experts.component.css']
})
export class ExpertsComponent implements OnInit {

  cookieToken: string = "";
  constructor(private cookieservice: CookiesService) { }

  ngOnInit(): void {
    this.cookieToken = this.cookieservice.getAuth();
  }


}
