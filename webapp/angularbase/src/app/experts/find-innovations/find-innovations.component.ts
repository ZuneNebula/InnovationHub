import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ApiService } from 'src/app/services/api.service';
import { ServerService } from 'src/app/services/server.service';
import { InnovationDetailsComponent } from '../innovation-details/innovation-details.component';
import { Innovation } from 'src/app/innovation';
import { Expert } from 'src/app/expert';
import { CookiesService } from 'src/app/services/cookies.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-find-innovations',
  templateUrl: './find-innovations.component.html',
  styleUrls: ['./find-innovations.component.css']
})
export class FindInnovationsComponent implements OnInit {

  constructor(private serverService: ServerService, private apiService: ApiService, private dialog: MatDialog, private cookies: CookiesService, private router: Router) { }

  allDomainsChip: boolean = true;

  wait!:boolean;

  innovationsCount!: number;

  cookieValue!: Expert;

  search_text: string = "";

  chipSelected!: boolean;

  chipColor:string='pink';


  //for pagination
  page: number = 0;
  size: number = 6;
  setRecentFilter: boolean = true;
  totalPages!: number;

  statuss = [
    { name: 'active' },
    { name: 'complete' }
  ];

  expert!: Expert;
  specializaion!: string[];

  innovations: any = [];
  ngOnInit(): void {
    this.wait=false;

    this.cookieValue = this.cookies.login();
    
    this.serverService.getExpert(this.cookieValue.expertId).subscribe((data) => {
      this.expert = data;
      this.specializaion = data.specialization;
      this.specializaion.forEach((element) => {
        if (element == "Any") {
          this.serverService.allInnovations(this.page, this.size, true).subscribe((data) => {
            this.wait=true;
            this.innovations = data.allInnovations;
            this.totalPages = data.totalpages;
            this.innovationsCount = data.resultsCount;
          })
        }
        else {
          this.serverService.filterInnovationsForExpertsView("", this.page, this.size, true, this.specializaion).subscribe((data) => {
            this.wait=true;
            this.innovations = data.innovations;
            
            this.totalPages = data.totalpages;
            this.innovationsCount = data.count;
          });
        }
      })

    })
  }

  openForm(innoid: string) {
    this.router.navigateByUrl('experts/form/' + innoid);
  }

  anySelected!: boolean;
  selected!:boolean;
  selectedDomain!: string;

  displayInnovationsOfThisSpecialization(domainSelected: string) {
    this.chipColor='blue';
    this.selected=true;
    this.selectedDomain = domainSelected;
    this.chipSelected = true;

    if (domainSelected == "Any") {
      this.anySelected = true;
      this.innovations = [];
      this.serverService.allInnovations(0, this.size, true).subscribe((data) => {
        this.wait=true;
        this.innovations = data.allInnovations;
        this.totalPages = data.totalpages;
        this.innovationsCount = data.resultsCount;
      })
    }
    else {
      const specializationSelected = [];
      specializationSelected.push(domainSelected);
      this.serverService.filterInnovationsForExpertsView("", 0, this.size, true, specializationSelected).subscribe((data) => {
        this.wait=true;
        this.innovations = data.innovations;
        this.totalPages = data.totalpages;
        this.innovationsCount = data.count;

      });
    }
  }

  displayAllInnovations() {
    window.location.reload();
  }

  domains: any = ['health', 'technology', 'environment', 'society'];



  sendInnovationId(id: string) {
    this.router.navigateByUrl('experts/form');
    this.apiService.setInnovationId(id);
  }

  openInnovation(id: string) {
    this.serverService.setInnovationId(id);
    this.dialog.open(InnovationDetailsComponent);
  }

  //for infinite scrolling
  addMore() {
    this.specializaion.forEach((element) => {
      if (element == "Any" || this.anySelected) {
        this.page = this.page + 1;
        this.serverService.allInnovations(this.page, this.size, true).subscribe((data) => {
          this.wait=true;
          for (let d of data.allInnovations) {
            this.innovations.push(Object.assign({}, d));
          }
        });
      }
      else {
        this.page = this.page + 1;
        this.serverService.filterInnovationsForExpertsView("", this.page, this.size, true, this.specializaion).subscribe((data) => {
          this.wait=true;
          for (let d of data.innovations) {
            this.innovations.push(Object.assign({}, d));
          }
        });
      }
    })


  }

  //recent filter
  recentFilter() {
    this.setRecentFilter = false;
    this.innovations = [];
    this.page = 0;
    this.serverService.filterInnovationsForExpertsView("", this.page, this.size, false, this.specializaion).subscribe((data) => {
      this.wait=true;
      this.innovations = data.innovations;
      this.totalPages = data.totalpages;
    })
  }

  //status filter
  statusFilter(status: string) {
    if (!this.chipSelected) {
      this.serverService.filterInnovationsForExpertsView(status, this.page, this.size, true, []).subscribe(data => {
        this.wait=true;
        this.innovations = data.innovations;
        console.log("innovations based on status: ", this.innovations);

        this.totalPages = data.totalpages;
      })
    }
    else {
      const specializationSelected = [];
      specializationSelected.push(this.selectedDomain);
      this.serverService.filterInnovationsForExpertsView(status, 0, this.size, true, specializationSelected).subscribe((data) => {
        this.wait=true;
        console.log("domain has length");
        this.innovations = data.innovations;
        this.totalPages = data.totalpages;
        this.innovationsCount = data.count;
      });
    }



  }



  //clear filter
  clearFilters() {
    this.innovations = [];
    this.setRecentFilter = true;
    this.page = 0;
    this.ngOnInit();
  }

  // for search operation
  search() {
    if (this.search_text.length == 0) {
      if (this.anySelected) {
        this.innovations = [];
        this.serverService.allInnovations(0, this.size, true).subscribe((data) => {
          this.wait=true;
          this.innovations = data.allInnovations;
          this.totalPages = data.totalpages;
        })
      }
      else if(!this.selected){
        this.innovations = [];
        this.serverService.filterInnovationsForExpertsView("",0,this.size,true,this.specializaion).subscribe((data)=>{
          this.wait=true;
          this.innovations = data.innovations;
          this.totalPages = data.totalpages;
        })
      }
      else {
        this.innovations = [];
        const specializationArray = [];
        specializationArray.push(this.selectedDomain);
        console.log("selected domain: "+this.selectedDomain);
        
        this.serverService.filterInnovationsForExpertsView("", 0, this.size, true, specializationArray).subscribe((data) => {
          this.wait=true;
          this.innovations = data.innovations;
          this.totalPages = data.totalpages;
        });
      }

    }
    else {
      this.serverService.getInnovationsBySearch(this.search_text).subscribe((data: Innovation[]) => {
        this.wait=true;
        this.innovations = [];
        this.innovations = data;
      });
    }
  }
}
