import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Expert } from 'src/app/expert';
import { Comment} from 'src/app/comment';
import { Innovator } from 'src/app/innovator';
import { CookiesService } from 'src/app/services/cookies.service';
import { ServerService } from 'src/app/services/server.service';

@Component({
  selector: 'app-innovation-details',
  templateUrl: './innovation-details.component.html',
  styleUrls: ['./innovation-details.component.css']
})
export class InnovationDetailsComponent implements OnInit {

  constructor(private server:ServerService, private sanitizer:DomSanitizer, private cookies:CookiesService,
    ) { }

  innovationIdFromService!:string;

  search_text: string="";
  

  innovation:any={};

  cookieValue!:Expert;

  allComments: Comment[] = [];


  ngOnInit(): void {
    this.allComments = this.innovation.comments;

    this.innovationIdFromService=this.server.getInnovationId();
    const getInnovationById=this.server.getInnovationById(this.innovationIdFromService);
    getInnovationById.subscribe((data:{})=>{
      this.innovation=data;
    });
    this.attachmentsArray=this.innovation.supportingDoc;

    this.cookieValue=this.cookies.login();
  }

  viewdoc(content:string){
    this.str=content;
    const data = atob(this.str.substring(this.str.indexOf(',')+1));
    const blob = new Blob([data], { type: 'application/octet-stream' });
    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }

  attachmentsArray:any=[];

  
  

  str!:string;
  fileUrl:any;

  detailsEnabled:boolean=true;

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

  addComment(event:any){
    this.commentObj.comment_data=this.comment;
    this.commentObj.dateOfComment=this.date;
    this.commentObj.commentator_name=this.cookieValue.firstName+" "+this.cookieValue.lastName;
    const tempData = [...this.innovation.comments];
    tempData.push(Object.assign({},this.commentObj));
    this.innovation.comments = [...tempData];

    const updateObservable=this.server.updateInnovation(this.innovation);
    updateObservable.subscribe((data)=>{
    });

    this.comment='';
    // event.target.value='';
  }

  

}
