import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ExpertsRoutingModule } from './experts-routing.module';
import { ExpertsComponent } from './experts.component';
import { ExpertsDashboardComponent } from './experts-dashboard/experts-dashboard.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { FormsModule } from '@angular/forms';
import { ExpertsProfileComponent } from './experts-profile/experts-profile.component';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { ProposalFormComponent } from './proposal-form/proposal-form.component';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogComponent } from './dialog/dialog.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ProposalListComponent } from './proposal-list/proposal-list.component';
import { FindInnovationsComponent } from './find-innovations/find-innovations.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { EditProposalComponent } from './edit-proposal/edit-proposal.component';
import {MatChipsModule} from '@angular/material/chips';
import { MatSelectModule } from '@angular/material/select';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { InnovationDetailsComponent } from './innovation-details/innovation-details.component';
import { ScrollingModule } from '@angular/cdk/scrolling';
import {MatTabsModule} from '@angular/material/tabs';
import { DateAgoExpertPipe } from '../services/date-ago-expert.pipe';
import {MatDividerModule} from '@angular/material/divider';
import {MatRadioModule} from '@angular/material/radio';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


import { MatMenuModule } from '@angular/material/menu';
import { ReversePipe } from '../services/reverse.pipe';
//import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { MatGridListModule } from '@angular/material/grid-list';
import { ConfirmDeleteComponent } from './confirm-delete/confirm-delete.component';
import {MatBadgeModule} from '@angular/material/badge';

@NgModule({
  declarations: [
    ExpertsComponent,
    ExpertsDashboardComponent,
    ExpertsProfileComponent,
    ProposalFormComponent,
    DialogComponent,
    ProposalListComponent,
    FindInnovationsComponent,
    EditProposalComponent,
    InnovationDetailsComponent,
    DateAgoExpertPipe,
    ReversePipe,
    ConfirmDeleteComponent
  ],
  entryComponents:[DialogComponent,InnovationDetailsComponent,ConfirmDeleteComponent],
  imports: [
    CommonModule,
    ExpertsRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTooltipModule,
    MatChipsModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatCardModule,
    ScrollingModule,
    MatMenuModule,
    MatTabsModule,
    MatDividerModule,
    MatGridListModule,
    MatRadioModule,
    MatBadgeModule,
    MatProgressSpinnerModule
  ],
  exports:[DateAgoExpertPipe,ReversePipe]
  
})
export class ExpertsModule { }
