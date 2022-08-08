import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CookiesService } from './cookies.service';

@Injectable({
  providedIn: 'root'
})
export class AuthExpertService {

  constructor(private cookieService:CookiesService, private router:Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
      return this.authenticateUser(state.url);
  }


  authenticateUser(url: string) {
    if (this.cookieService.isExpert()) {
      return this.cookieService.isExpertLoggedIn;
    }
    this.cookieService.redirectUrl = url;
    return this.router.parseUrl('/');
  }
}
