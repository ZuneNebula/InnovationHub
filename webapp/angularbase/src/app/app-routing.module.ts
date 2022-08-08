import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthExpertService } from './services/auth-expert.service';
import { AuthInnovatorService } from './services/auth-innovator.service';
import { ExpertsProfileComponent } from './experts/experts-profile/experts-profile.component';
import { InnovatorsProfileComponent } from './innovators/innovators-profile/innovators-profile.component';

const routes: Routes = [
  {
    path:"", 
    component:HomeComponent,
    children: [
      
    ]
  },
  { 
    path: 'experts',
    canActivate:[AuthExpertService],
    loadChildren: () => import('./experts/experts.module').then(m => m.ExpertsModule) 
  },
  { 
    path: 'innovators', 
    canActivate:[AuthInnovatorService],
    loadChildren: () => import('./innovators/innovators.module').then(m => m.InnovatorsModule) 
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
