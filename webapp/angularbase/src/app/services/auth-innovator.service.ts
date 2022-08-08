import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CookiesService } from './cookies.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInnovatorService implements CanActivate{

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
    if (this.cookieService.isInnovator()) {
      return this.cookieService.isInnovatorLoggedIn;
    }
    this.cookieService.redirectUrl = url;
    return this.router.parseUrl('/');
  }
}
