import { ExpertsDashboardComponent } from './experts-dashboard/experts-dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExpertsProfileComponent } from './experts-profile/experts-profile.component';
import { ProposalFormComponent } from './proposal-form/proposal-form.component';
import { ProposalListComponent } from './proposal-list/proposal-list.component';
import { FindInnovationsComponent } from './find-innovations/find-innovations.component';
import { EditProposalComponent } from './edit-proposal/edit-proposal.component';

const routes: Routes = [
  { 
    path: '', 
    component: ExpertsDashboardComponent,
    children:[
      {
        path:"",
        component:ProposalListComponent
      },
      {
        path: 'profile',
        component: ExpertsProfileComponent
      },
      {
        path:'form/:innovationId',
        component:ProposalFormComponent
      },
      
      {
        path:"find",
        component:FindInnovationsComponent
      },
      {
        path:"edit/:proposalId",
        component:EditProposalComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExpertsRoutingModule { }
