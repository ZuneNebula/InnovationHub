import { Expert } from './../expert';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Innovator } from '../innovator';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class CookiesService {
  currentInnovator: Innovator ={
    innovatorId:'',
    username: '',
    firstName: '',
    lastName:'',
    avatarUrl: '',
    email: '',
    occupation: '',
    updatedBy: '',
    updatedOn: new Date,
    tags:[]
  }
  currentExpert: Expert= {
    expertId: '',
    username: '',
    firstName: '',
    lastName: '',
    avatarUrl: '',
    email: '',
    occupation: '',
    updatedBy: '',
    updatedOn: new Date,
    tags:[],
    specialization: [],
    rating: 0.0
  }
  isExpertLoggedIn:boolean = false;
  isInnovatorLoggedIn: boolean = false;
  redirectUrl:string ="";

  helper = new JwtHelperService();
  constructor(private cookie: CookieService) { }

  login(): any{
    const decodedToken = this.helper.decodeToken(this.getAuth());
    if(this.isInnovatorLoggedIn){
      
      this.currentInnovator.innovatorId = decodedToken.innovatorId;
      this.currentInnovator.username = decodedToken.username;
      this.currentInnovator.firstName = decodedToken.firstName;
      this.currentInnovator.lastName = decodedToken.lastName;
      this.currentInnovator.avatarUrl = decodedToken.avatar;
      this.currentInnovator.email = decodedToken.email;
      return this.currentInnovator
    }
    else{
      this.currentExpert.expertId = decodedToken.expertId;
      this.currentExpert.username = decodedToken.username;
      this.currentExpert.firstName = decodedToken.firstName;
      this.currentExpert.lastName = decodedToken.lastName;
      this.currentExpert.avatarUrl = decodedToken.avatar;
      this.currentExpert.email = decodedToken.email;
      return this.currentExpert;
    }
  }

  isInnovator():boolean {
    if(this.cookie.check("JWT-TOKEN")){
      const decodedToken = this.helper.decodeToken(this.getAuth());
    if(decodedToken.innovatorId){
      this.isInnovatorLoggedIn = true;
      return true;
    }
    }
    
    return false;
    
  }

  
  isExpert():boolean {
    if(this.cookie.check("JWT-TOKEN")){
      const decodedToken = this.helper.decodeToken(this.getAuth());
    if(decodedToken.expertId){
      this.isExpertLoggedIn = true;
      return true;
    }
    }
    
    return false;
    
  }

  getAuth(){
    return this.cookie.get("JWT-TOKEN");
  }

  setAuth(value:string){
    this.cookie.set("JWT-TOKEN", value);
  }

  deleteAuth(){
    this.cookie.delete("JWT-TOKEN");
  }
}
