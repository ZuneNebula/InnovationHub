import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Attachment } from 'src/app/attach';
import { Expert } from 'src/app/expert';
import { Innovation } from 'src/app/innovation';
import { PrivateComment } from 'src/app/private-comment';
import { Proposal } from 'src/app/proposal';
import { ApiService } from 'src/app/services/api.service';
import { CookiesService } from 'src/app/services/cookies.service';
import { ServerService } from 'src/app/services/server.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  constructor(private apiService: ApiService, @Inject(MAT_DIALOG_DATA) public data: any, private sanitizer: DomSanitizer, private service: ServerService, private cookies:CookiesService) { }

  buttonText!:string;
  headerText!:string;

  buttonFlag:boolean=false;

  changeContent(){
    this.buttonFlag=!this.buttonFlag;
    this.displayTextOnButton();
  }

  displayTextOnButton(){
    if(this.buttonFlag){
      this.buttonText="Display Proposal";
      this.headerText="Private Comments";
    }
    else{
      this.buttonText="Private Comments";
      this.headerText="Proposal Details"
    }
  }

  cookieValue!:Expert;
  commentsLength!: number;
  allPrivateComments: PrivateComment[] = [];

  proposalToBeEdited: Proposal={
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
    coverImage : {
      content:"",
      fileName:"",
      extension: "",
      fileType:""
    },
    attachment: [],
    privateComments: []
  }

  innovation: any = {};
  innovationIdFromProposal!: string;

  ngOnInit(): void {

    this.displayTextOnButton();

    const getByIdObservable = this.apiService.getProposalById(this.data.proposalId);
    getByIdObservable.subscribe((proposal) => {
      this.proposalToBeEdited = proposal;
      this.innovationIdFromProposal = proposal.innovationId;

      const getInnovationById = this.service.getInnovationById(this.innovationIdFromProposal);
      getInnovationById.subscribe((obj) => {
        this.innovation = obj;
      });
    });

    this.cookieValue = this.cookies.login();

  }


  a: Attachment = {
    fileName: '',
    extension: '',
    content: '',
    fileType: ''
  }
  b: Attachment = {
    fileName: '',
    extension: '',
    content: '',
    fileType: ''
  }
  contentData!: any
  onChange(event: any, typeOfFile: string) {
    this.a.fileType = typeOfFile;
    let file = event.target.files[0];

    this.a.fileName = file.name.split(".")[0];
    this.a.extension = file.name.split(".")[1];

    let filereader = new FileReader();
    filereader.readAsDataURL(file);
    filereader.onload = () => {
      this.contentData = filereader.result;
      this.a.content = this.contentData;

      this.proposalToBeEdited.coverImage = this.a;
    }
  }

  onChangeDoc(event: any, typeOfFile: string) {
    this.b.fileType = typeOfFile;
    let file = event.target.files[0];

    this.b.fileName = file.name.split(".")[0];
    this.b.extension = file.name.split(".")[1];

    let filereader = new FileReader();
    filereader.readAsDataURL(file);
    filereader.onload = () => {
      this.contentData = filereader.result;
      this.b.content = this.contentData;

      this.proposalToBeEdited.attachment.push(this.b);
    }
  }

  update() {
    const updateObservable = this.apiService.updateProposal(this.proposalToBeEdited);
    updateObservable.subscribe((data: {}) => {
    })
    window.location.reload();
  }

  proposalToBeDeleted!: Proposal;
  idToBeDeleted: number = 10;

  delete() {
    const deleteObservable = this.apiService.deleteProposalById(this.data.proposalId);
    deleteObservable.subscribe((obj) => {
      this.proposalToBeDeleted = obj;
    })
    window.location.reload();
  }

  fileUrl: any;
  str!: string;
  viewdoc(content:string){
    this.str=content;
    const data = atob(this.str.substring(this.str.indexOf(',')+1));
    const blob = new Blob([data], { type: 'application/octet-stream' });
    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }

  image: boolean = false;
  doc: boolean = false;

  imageClicked() {
    this.image = true;
  }

  docClicked() {
    this.doc = true;
  }

  commentObj:PrivateComment={
    commentator: null,
    content: null,
    commentDate: null,
    avatarUrl: null
  }

  comment:string ="";

  addComment(){
    this.commentObj.content=this.comment;
    this.commentObj.commentDate=new Date();
    this.commentObj.commentator=this.cookieValue.firstName+" "+this.cookieValue.lastName;
    this.commentObj.avatarUrl = this.cookieValue.avatarUrl;
    const tempData = [...this.proposalToBeEdited.privateComments];
    tempData.push(Object.assign({},this.commentObj));
    this.proposalToBeEdited.privateComments = [...tempData];

    const updateObservable=this.apiService.updateProposal(this.proposalToBeEdited);
    updateObservable.subscribe((data)=>{
    });
    this.comment = "";
  }
}
