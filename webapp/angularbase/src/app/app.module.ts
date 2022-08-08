import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { CookieService } from 'ngx-cookie-service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthInnovatorService } from './services/auth-innovator.service';
import { AuthExpertService } from './services/auth-expert.service';
import {MatMenuModule} from '@angular/material/menu';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { IvyCarouselModule } from 'angular-responsive-carousel';
import { MatGridListModule } from '@angular/material/grid-list';
import { ReversePipe } from './services/reverse.pipe';
import { ReverseInnovatorPipe } from './services/reverse-innovator.pipe';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ReverseInnovatorPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatTabsModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    MatMenuModule,
    IvyCarouselModule,
    MatGridListModule

  ],
  providers: [CookieService, HttpClientModule, AuthInnovatorService, AuthExpertService],
  bootstrap: [AppComponent]
})
export class AppModule { }
