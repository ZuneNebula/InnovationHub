import { Innovation } from './../../innovation';
import { Innovator } from 'src/app/innovator';
import { Router } from '@angular/router';
import { ServerService } from 'src/app/services/server.service';
import { Component,  OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { CookiesService } from 'src/app/services/cookies.service';
import { MatDialog } from '@angular/material/dialog';
import { ViewInnovationComponent } from '../view-innovation/view-innovation.component';
import { DateAgoPipe } from 'src/app/services/date-ago.pipe';

@Component({
  selector: 'app-innovations-list',
  templateUrl: './innovations-list.component.html',
  styleUrls: ['./innovations-list.component.css']
})
export class InnovationsListComponent implements OnInit {
  
  innovatorValue!: Innovator;
  allInnovations!: Innovation[];
  search_text: string="";
  rating: number=0;
   ratingArr:number[] = [];
  starCount: number = 5;
  page: number = 0;
  size: number = 6;
  totalPages: number = 0;
  setRecentFilter: boolean = false;
  totalInnovationsCount!: number;
  totalComments: number = 0;
  wait: boolean = false;
  
  domains = [
    { name: 'Science & Technology' },
    { name: 'Health Sciences' },
    { name: 'Construction' },
    { name: 'Management' },
    { name: 'Environment' },
    { name: 'Power & Energy' },
    { name: 'Society' },
    { name: 'Food' },
    { name: 'Education'},
    { name: 'E-Commerce'},
    { name: 'Other' }
  ];

  constructor(private serverservice: ServerService, private router: Router,private sanitizer:DomSanitizer,
    private cookies: CookiesService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.innovatorValue = this.cookies.login();
      this.serverservice.getInnovationsByFilter("",this.page,this.size,
      false).subscribe((data) =>{
        this.wait = true;
        this.allInnovations = data.innovations;
        this.totalPages = data.totalpages;
        this.totalInnovationsCount = data.totalInnovations;
      });
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }  
  }
  onSelectionChange(innovation: Innovation){}

  delete(innovation: Innovation,i: number){
    if(innovation.innovationId){
      this.serverservice.deleteInnovation(innovation.innovationId).subscribe(response => {
        
      });
      window.location.reload();
    }
  }

  transform(innovation: Innovation){
    if(innovation.coverPhoto.content)
      return this.sanitizer.bypassSecurityTrustUrl(innovation.coverPhoto.content);
    return "";
  }

viewInnovation(innovation: Innovation){
  if(innovation)
    this.router.navigate(["view",innovation.innovationId])
}


  openDialog(innovation : Innovation){
    const dialogRef = this.dialog.open(ViewInnovationComponent, {
      // height:"80%",
      width: "55%",
      data: innovation,
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }

  //for search feature  
  search(){
    if (this.search_text.length == 0) {
        this.allInnovations=[];
        this.serverservice.getInnovationsByFilter("",this.page,this.size,
        false).subscribe((data) =>{
          this.allInnovations = data.innovations;
          this.totalPages = data.totalpages;
        });
    }
    else{
      
    this.serverservice.getInnovationsBySearch(this.search_text).subscribe((data: Innovation[])=>{
      this.allInnovations=[];
      this.allInnovations = data;
      
    })
    }
    
  }

  
  //for infinite scrolling
  addMore(){
    this.page = this.page + 1;
    this.serverservice.getInnovationsByFilter("",this.page,this.size,
    false)
    .subscribe((data) => {
        for(let d of data.innovations){
            this.allInnovations.push(Object.assign({},d));
        }
      });
  }
  
  //recent filter
  recentFilter(){
    this.setRecentFilter = true;
    this.allInnovations = [];
    this.page = 0;
    this.serverservice.getInnovationsByFilter("",this.page,this.size,
      true)
    .subscribe(data => {
        this.allInnovations = data.innovations;
        this.totalPages = data.totalpages;
    })
  }

  //domain filter
  domainFilter(domain: string){
    this.allInnovations = [];
    this.page = 0;
    this.setRecentFilter = true;
    this.serverservice.getInnovationsByFilter(domain,this.page,this.size,false)
    .subscribe(data => {
        this.allInnovations = data.innovations;
        this.totalPages = data.totalpages;
    })
  }

  //clear filter
  clearFilters(){
    this.allInnovations = [];
    this.setRecentFilter = false;
    this.page = 0;
    this.ngOnInit();
  }  

  returnLength(array: any[]){
    if(array){
      return array.length;
    }
    return 0;
  }
}
