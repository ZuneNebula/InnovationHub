import { ViewProposalComponent } from './view-proposal/view-proposal.component';
import { InnovationsListComponent } from './innovations-list/innovations-list.component';
import { ViewInnovationComponent } from './view-innovation/view-innovation.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InnovatorsDashboardComponent } from './innovators-dashboard/innovators-dashboard.component';
import { InnovatorsProfileComponent } from './innovators-profile/innovators-profile.component';
import { InnovatorsComponent } from './innovators.component';
import { NewInnovationComponent } from './new-innovation/new-innovation.component';

const routes: Routes = [
  { path: '', component: InnovatorsDashboardComponent,
   children:[
     { path:'', component: InnovationsListComponent},
    { path: 'profile',component: InnovatorsProfileComponent },
    //{ path:"newInnovation", component: NewInnovationComponent},
    { path:"view", component: NewInnovationComponent },
    { path:"view/:innovationId", component: NewInnovationComponent },
    { path:"proposal/:proposalId", component: ViewProposalComponent }
   ] 
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InnovatorsRoutingModule { }
