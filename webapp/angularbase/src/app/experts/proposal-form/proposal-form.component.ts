import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Attachment } from 'src/app/attach';
import { Expert } from 'src/app/expert';
import { Proposal } from 'src/app/proposal';
import { ApiService } from 'src/app/services/api.service';
import { CookiesService } from 'src/app/services/cookies.service';
import { ServerService } from 'src/app/services/server.service';
@Component({
  selector: 'app-proposal-form',
  templateUrl: './proposal-form.component.html',
  styleUrls: ['./proposal-form.component.css']
})
export class ProposalFormComponent implements OnInit {
  wait!:boolean;

  newProposal = this.fb.group({
    proposalTitle: [null, Validators.required],
    proposalDescription: ['', Validators.required],
    coverImage: [null],
    docFiles: [],
    fileTitle:""
  });
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
  get docFiles() {
    return this.newProposal.get('docFiles') as FormArray;
  }
  addFile() {
    this.docFiles.push(this.fb.control(''));
  }

  image: any; //has file content
  file: any;  //has file content
  imageFile: any;// has the complete file
  docFile: any;// has the complete file
  count: number = 0;
  addedFiles: Attachment[] = [];

  expertValue: Expert = {
    expertId: '',
    username: '',
    firstName: '',
    lastName: '',
    avatarUrl: '',
    email: '',
    specialization: []
  }
  attachment: Attachment = {
    fileName: '',
    extension: '',
    content: '',
    fileType: ''
  }

  newProposalValue: Proposal = {
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
      fileName: "",
      extension: "",
      content: "",
      fileType: ""
    },
    attachment: [],
    privateComments: []
  }

  innovationId!: string;
  proposalId!: string;

  constructor(private fb: FormBuilder, private cookie: CookiesService, private server: ServerService, private _snackBar: MatSnackBar
    , private router: Router, private apiService: ApiService, private activatedRoute: ActivatedRoute, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.expertValue = this.cookie.login();

    this.innovationId = this.activatedRoute.snapshot.paramMap.get('innovationId') as string;
    this.proposalId = this.activatedRoute.snapshot.paramMap.get('proposalId') as string;

    if (!this.proposalId) {
      const getInnovationById = this.server.getInnovationById(this.innovationId);
      getInnovationById.subscribe((data) => {
        this.wait=true;
        this.innovation = data;
      })
    }
    else {
      const getProposalById = this.apiService.getProposalById(this.proposalId);
      getProposalById.subscribe((data) => {
        this.wait=true;
        this.newProposalValue = data;

        this.newProposal.controls['proposalTitle'].setValue(this.newProposalValue.proposalTitle);
        this.newProposal.controls['proposalDescription'].setValue(this.newProposalValue.proposalDescription);
        this.addedFiles = this.newProposalValue.attachment;
        this.tempFileArray = [...this.newProposalValue.attachment];

        const getInnovationById = this.server.getInnovationById(this.newProposalValue.innovationId);
        getInnovationById.subscribe((data) => {
          this.innovation = data;
        })
      })
    }

  }

  str!: string;
  fileUrl: any;
  viewdoc(content:string){
    this.str=content;
    const data = atob(this.str.substring(this.str.indexOf(',')+1));
    const blob = new Blob([data], { type: 'application/octet-stream' });

    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }
  // viewdoc() {
  //   this.str = this.innovation.attachment[1].content;
  //   const data = atob(this.str.substring(this.str.indexOf(',') + 1));
  //   const blob = new Blob([data], { type: 'application/octet-stream' });
  //   this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  // }


  innovation: any = {
  }

  onChange(event: any) {
    this.imageFile = event.target.files[0];
    let filereader = new FileReader();
    filereader.readAsDataURL(this.imageFile);
    filereader.onload = () => {
      this.image = filereader.result;
      this.newProposalValue.coverImage.fileName = this.imageFile.name.split(".")[0];
      this.newProposalValue.coverImage.extension = this.imageFile.name.split(".")[1];
      this.newProposalValue.coverImage.content = this.image;
      this.newProposalValue.coverImage.fileType = "";
    };
  }

  removeCoverImage(){
    this.newProposalValue.coverImage.fileName = "";
    this.newProposalValue.coverImage.fileType = "";
    this.newProposalValue.coverImage.extension = "";
    this.newProposalValue.coverImage.content = "";
  }

  tempFileArray: any = [];

  fileTitles!: string;

  onChanges(event: any) {
    this.docFile = event.target.files[0];
    let filereader = new FileReader();
    filereader.readAsDataURL(this.docFile);
    filereader.onload = () => {
      this.attachment.content = filereader.result as string;
      if(this.newProposal.controls['fileTitle'].value)
        this.attachment.fileName = this.newProposal.controls['fileTitle'].value;
      else
        this.attachment.fileName = this.docFile.name.split(".")[0];
      this.attachment.extension = this.docFile.name.split(".")[1];
      this.attachment.fileType = this.newProposal.controls['fileTitle'].value;
    };
  }

  backToInnovations() {
    this.router.navigateByUrl('experts/find');
  }


  reset() {
    this.tempFileArray.push(Object.assign({}, this.attachment));
    this.docFile = null;
    this.newProposal.controls['docFiles'].reset();
  }
  remove(i: number) {
    this.tempFileArray.splice(i, 1)
  }
  onSubmit(): void {
    this.cookie.isInnovatorLoggedIn = true;

    this.newProposalValue.proposalTitle = this.newProposal.controls['proposalTitle'].value;
    this.newProposalValue.expertId = this.expertValue.expertId;
    this.newProposalValue.innovationId = this.innovation.innovationId;

    this.newProposalValue.expertName = this.expertValue.firstName + " " + this.expertValue.lastName;
    this.newProposalValue.proposalDescription = this.newProposal.controls['proposalDescription'].value;

    this.newProposalValue.domain = this.innovation.domain;

    this.newProposalValue.statusOfProposal = "pending";


    this.newProposalValue.attachment = this.tempFileArray;
    this.apiService.postProposal(this.newProposalValue).subscribe((data) => {
      this.router.navigateByUrl('experts');
      this._snackBar.open('Saved Successfully', "", {
        duration: 2000
      });
    });


  }





}
