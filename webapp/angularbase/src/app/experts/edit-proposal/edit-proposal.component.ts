import { L } from '@angular/cdk/keycodes';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Attachment } from 'src/app/attach';
import { Proposal } from 'src/app/proposal';
import { ApiService } from 'src/app/services/api.service';
import { ServerService } from 'src/app/services/server.service';

@Component({
  selector: 'app-edit-proposal',
  templateUrl: './edit-proposal.component.html',
  styleUrls: ['./edit-proposal.component.css']
})
export class EditProposalComponent implements OnInit {
  wait!:boolean;

  constructor(private apiService:ApiService, private sanitizer:DomSanitizer, private activatedRoute:ActivatedRoute,
    private router: Router, private server:ServerService) { }

  proposalId:string=this.apiService.getProposalId();

  id!:string;

  proposalObj:Proposal={
    proposalId: '',
    expertId: '',
    innovationId: '',
    proposalTitle: '',
    proposalDescription: '',
    expertName: '',
    statusOfProposal: '',
    domain: '',
    rating: 0,
    dateOfCreation: new Date(),
    coverImage: {
      "fileName": '',
      "extension": '',
      "content": '',
      "fileType": ''
    },
    attachment: [],
    privateComments: []
  }

  innovation:any={}

  ngOnInit(): void {
    this.id=this.activatedRoute.snapshot.paramMap.get('proposalId') as string;
    const getObservable=this.apiService.getProposalById(this.id);
    getObservable.subscribe((data)=>{
      this.wait=true;
      this.proposalObj=data;
      const getInnovation=this.server.getInnovationById(data.innovationId);
      getInnovation.subscribe((obj)=>{
        this.wait=true;
        this.innovation=obj;
      })
    })
  }
  str!:string;
  fileUrl:any;
  viewdoc(content:string){
    this.str=content;
    const data = atob(this.str.substring(this.str.indexOf(',')+1));
    const blob = new Blob([data], { type: 'application/octet-stream' });

    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }

  dashboard(){
    this.router.navigateByUrl("/experts");
  }

  update(){
    this.apiService.updateProposal(this.proposalObj).subscribe((data)=>{
      this.router.navigateByUrl("experts");
    })
  }
  proposalToBeDeleted!:Proposal;

  delete(){
    const deleteObservable = this.apiService.deleteProposalById(this.proposalObj.proposalId);
    deleteObservable.subscribe((obj)=>{
      this.proposalToBeDeleted=obj;
    })
    window.location.reload();
  }

  

  a:Attachment={
    fileName: '',
    extension: '',
    content: '',
    fileType: ''
  }
  b:Attachment={
    fileName: '',
    extension: '',
    content: '',
    fileType: ''
  }
  contentData!:any
  onChange(event:any, typeOfFile:string){
    this.a.fileType = typeOfFile; 
    let file = event.target.files[0];

    
    this.a.fileName = file.name.split(".")[0];
    this.a.extension = file.name.split(".")[1];
   
    let filereader = new FileReader();
    filereader.readAsDataURL(file);
    filereader.onload=()=>{
      this.contentData=filereader.result;
      this.a.content = this.contentData; 
      this.proposalObj.coverImage=this.a; 
    }
  }

  onChangeDoc(event:any, typeOfFile:string){
    this.b.fileType = typeOfFile; 
    let file = event.target.files[0];
    
    this.b.fileName = file.name.split(".")[0];
    this.b.extension = file.name.split(".")[1];
   
    let filereader = new FileReader();
    filereader.readAsDataURL(file);
    filereader.onload=()=>{
      this.contentData=filereader.result;
      this.b.content = this.contentData; 
      this.proposalObj.attachment.push(this.b);
    }
  }
}
