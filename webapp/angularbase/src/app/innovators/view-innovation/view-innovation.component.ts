import { Router } from '@angular/router';
import { Comment } from './../../comment';
import { Proposal } from 'src/app/proposal';
import { ApiService } from 'src/app/services/api.service';
import { Innovator } from 'src/app/innovator';
import { Innovation } from './../../innovation';
import { ServerService } from 'src/app/services/server.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { CookiesService } from 'src/app/services/cookies.service';

@Component({
  selector: 'app-view-innovation',
  templateUrl: './view-innovation.component.html',
  styleUrls: ['./view-innovation.component.css']
})
export class ViewInnovationComponent implements OnInit {

  

  constructor(public dialogRef: MatDialogRef<ViewInnovationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Innovation,private server:ServerService, private sanitizer:DomSanitizer, private cookies:CookiesService
    ,private apiService: ApiService, private router: Router) { 
    }
  innovationIdFromService!:string;

  search_text: string="";
  rating: number=0;
  ratingArr:number[] = [];
  starCount: number = 5;
  allProposals: Proposal[] = [];//stores all proposals for this innovation
  allComments: Comment[] = [];
  page: number = 0;
  size: number = 4;
  totalPages!:number;
  innovation:any={};//current innovation
  str!:string;
  fileUrl:any;
  totalProposals!: number;

  cookieValue!:Innovator;


  ngOnInit(): void {
    this.innovation = this.data;
    this.allComments = this.innovation.comments;
    
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }

    this.attachmentsArray=this.innovation.supportingDoc;

    this.cookieValue=this.cookies.login();

    this.apiService.getProposalsByInnovation(this.innovation.innovationId,this.page,this.size).subscribe(data =>{
      this.allProposals = data.proposals;
      this.totalPages = data.totalPages;
      this.totalProposals = data.totalProposals;
    })
  }

  addMore(){
    this.page = this.page + 1;
    this.apiService.getProposalsByInnovation(this.innovation.innovationId,this.page,this.size).subscribe(data => {
      for(let d of data.proposals){
        this.allProposals.push(Object.assign({},d));
      }
    })
  }

  transform(proposal: Proposal){
    if(proposal.coverImage.content)
      return this.sanitizer.bypassSecurityTrustUrl(proposal.coverImage.content);
    return "";
}

  viewdoc(content:string){
    this.str=content;
    const data = atob(this.str.substring(this.str.indexOf(',')+1));
    const blob = new Blob([data], { type: 'application/octet-stream' });

    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }

  attachmentsArray:any=[];

  onClick(rating:number) {
    this.rating = rating;
    return false;
  }

  color: string = 'accent';

  showIcon(index:number) {
    if (this.rating >=index+ 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }

  detailsEnabled:boolean=true;
  // commentsEnabled:boolean=false;

  enableDetails(){
    this.detailsEnabled=!this.detailsEnabled;
  }

  commentObj:Comment={
    commentator_name: null,
    comment_data: null,
    dateOfComment: null
  }
  
  comment:string="";
  date:Date=new Date();

  addComment(){
    this.commentObj.comment_data=this.comment;
    this.commentObj.dateOfComment=new Date();
    this.commentObj.commentator_name=this.cookieValue.firstName+" "+this.cookieValue.lastName;
    const tempData = [...this.allComments];
    tempData.push(Object.assign({},this.commentObj));
    this.allComments = [...tempData];
    this.innovation.comments = [...this.allComments]
    const updateObservable=this.server.updateInnovation(this.innovation);
    updateObservable.subscribe((data)=>{
    });
    this.comment = "";
  }

  onviewProposal(proposalId:string, index:number){
    this.dialogRef.close();
    this.router.navigate(["innovators/proposal/"+proposalId]);
  }
  
}
