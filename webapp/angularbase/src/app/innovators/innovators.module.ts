import { DateAgoPipe } from './../services/date-ago.pipe';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InnovatorsRoutingModule } from './innovators-routing.module';
import { InnovatorsComponent } from './innovators.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { LayoutModule } from '@angular/cdk/layout';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { InnovatorsDashboardComponent } from './innovators-dashboard/innovators-dashboard.component';
import { InnovatorsProfileComponent } from './innovators-profile/innovators-profile.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatMenuModule} from '@angular/material/menu';
import { InnovationsListComponent } from './innovations-list/innovations-list.component';
//import { NewInnovationComponent } from './new-innovation/new-innovation.component';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { MatTabsModule } from '@angular/material/tabs';
import {MatGridListModule} from '@angular/material/grid-list';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ViewInnovationComponent } from './view-innovation/view-innovation.component';
//import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import {MatChipsModule} from '@angular/material/chips';
import {MatDialogModule} from '@angular/material/dialog';
//import { DateAgoPipe } from '../services/date-ago.pipe';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { ReverseInnovatorPipe } from '../services/reverse-innovator.pipe';
import { NewInnovationComponent } from './new-innovation/new-innovation.component';
import { ViewProposalComponent } from './view-proposal/view-proposal.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
//import { ReversePipe } from '../services/reverse.pipe';
import {MatBadgeModule} from '@angular/material/badge';

@NgModule({
  declarations: [
    InnovatorsComponent,
    InnovatorsDashboardComponent,
    InnovatorsProfileComponent,
    InnovationsListComponent,
    NewInnovationComponent,
    ViewInnovationComponent,
    DateAgoPipe,
    ViewProposalComponent,
    
  ],
  imports: [
    CommonModule,
    //InfiniteScrollModule,
    MatDialogModule,
    MatTooltipModule,
    MatChipsModule,
    ScrollingModule,
    MatFormFieldModule,
    MatGridListModule,
    MatTabsModule,
    InnovatorsRoutingModule,
    MatSnackBarModule,
    MatToolbarModule,
    LayoutModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatChipsModule,
    MatBadgeModule,
    MatProgressSpinnerModule
  ],
  exports:[DateAgoPipe,]
})
export class InnovatorsModule { }
