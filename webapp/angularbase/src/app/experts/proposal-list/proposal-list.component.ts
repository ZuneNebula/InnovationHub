import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Expert } from 'src/app/expert';
import { Proposal } from 'src/app/proposal';
import { ApiService } from 'src/app/services/api.service';
import { CookiesService } from 'src/app/services/cookies.service';
import { ServerService } from 'src/app/services/server.service';
import { DialogComponent } from '../dialog/dialog.component';
import { Location } from '@angular/common';
import { ConfirmDeleteComponent } from '../confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.css']
})
export class ProposalListComponent implements OnInit {

  wait!:boolean;

  domains: any = ['health', 'technology', 'environment', 'society'];

  searchFlag: boolean = false;

  filterTerm!: string;

  search_text: string = "";

  page: number = 0;
  size: number = 6;
  totalPages: number = 0;
  setRecentFilter: boolean = true;
  allStatus = [
    { name: 'pending' },
    { name: 'accepted' }
  ];

  proposalsByDomain: any = [];

  filterApplied: boolean = false;

  selectDomain(domain: string) {
    this.filterApplied = true;
    const getByDomainObservable = this.apiService.getProposalsByDomain(domain);
    getByDomainObservable.subscribe((data: {}) => {
      this.wait=true;
      this.proposalsByDomain = data;
    })
  }

  constructor(private breakpointObserver: BreakpointObserver, private cookies: CookiesService, private router: Router,
    private serverService: ServerService, private modalService: NgbModal, private apiService: ApiService, private dialog: MatDialog, private location: Location) { }


  cookieValue!: Expert; //value from cookie

  proposals: any = [];

  results!:number;

  ngOnInit(): void {
    this.wait=false;
    
    this.cookieValue = (this.cookies.login());
    this.proposals = [];
    
    this.apiService.getProposalsByFilter("", this.page, this.size, true).subscribe((data) => {
      this.wait=true;
      this.proposals = data.allProposals;
      this.totalPages = data.totalpages;
      this.results = data.totalProposals;
    });
    
  }


  viewProposal(id: number) {
    this.cookieValue = (this.cookies.login());
  }


  viewProposalById(id: number) {
    this.dialog.open(DialogComponent, { data: { proposalId: id } });
  }
  
  proposalToBeDeleted!: Proposal;

  delete(id: string) {
    const dialogComp = this.dialog.open(ConfirmDeleteComponent,{data: {proposalId:id}});
    dialogComp.afterClosed().subscribe((proposalId)=>{
      console.log("Proposal Id: " +proposalId);
      const tempProposals = JSON.parse(JSON.stringify(this.proposals));
      const updatedProposalsList = tempProposals.filter((prp:any)=> prp.proposalId !== proposalId );
      this.proposals = updatedProposalsList;
    })
  }

  editProposal(id: string) {
    this.apiService.setProposalId(id);
  }

 

  //for search feature
  search() {
    if (this.search_text.length == 0) {
      this.apiService.getProposalsByFilter("", this.page, this.size, true).subscribe((data) => {
        this.wait=true;
        this.proposals=[];
        this.proposals = data.allProposals;
        this.totalPages = data.totalpages;
      });
    }
    else {
      this.apiService.getProposalsBySearch(this.search_text).subscribe((data:Proposal[])=> {
        this.wait=true;
        this.proposals = [];
        this.proposals = data;
      })
    }


  }


  addMore() {
    this.page = this.page + 1;
    
    this.apiService.getProposalsByFilter("", this.page, this.size, true).subscribe((data) => {
      this.wait=true;
      for (let d of data.allProposals) {
        this.proposals.push(Object.assign({}, d));
      }
      
    });
  }

  //recent filter
  recentFilter() {
    this.setRecentFilter = false;
    this.proposals = [];
    this.page = 0;
    this.apiService.getProposalsByFilter("", this.page, this.size, false).subscribe(data => {
      this.wait=true;
      this.proposals = data.allProposals;
      this.totalPages = data.totalpages;
    })
  }



  //status filter
  statusFilter(status: string) {
    console.log(status);    
    this.proposals = [];  
    this.page = 0;
    this.setRecentFilter = true;
    this.apiService.getProposalsByFilter(status, this.page, this.size, true).subscribe(data => {
      this.wait=true;
      this.proposals = data.allProposals;
      this.totalPages = data.totalpages;
    })
  }

  //clear filter
  clearFilters() {
    this.proposals = [];
    this.setRecentFilter = true;
    this.page = 0;
    this.ngOnInit();
  }

}
