import { Router, ActivatedRoute } from '@angular/router';
import { Observable, Subscriber } from 'rxjs';
import { Innovation } from './../../innovation';
import { ServerService } from 'src/app/services/server.service';
import { CookiesService } from './../../services/cookies.service';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, Validators, FormArray } from '@angular/forms';
import { Innovator } from 'src/app/innovator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Comment } from 'src/app/comment';
import { Files } from 'src/app/Files';

@Component({
  selector: 'app-new-innovation',
  templateUrl: './new-innovation.component.html',
  styleUrls: ['./new-innovation.component.css']
})
export class NewInnovationComponent {
  newInnovation = this.fb.group({
    innovationName: [null, Validators.required],
    innovationDesc: [null, Validators.required],
    challenges: [null, Validators.required],
    domain: [null, Validators.required],
    status: [null, Validators.required],
    imageFiles: [null],
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

  get docFiles(){
    return this.newInnovation.get('docFiles') as FormArray;
  }
  addFile(){
    this.docFiles.push(this.fb.control(''));
  }

  image: any; //has file content
  file: any;  //has file content
  imageFile: any;// has the complete file
  docFile: any;// has the complete file
  count: number = 0;
  id:string = "";
  comments!:Comment[];
  addedFiles: Files[]=[];
  innovation!: Innovation;

  innovatorValue: Innovator = { //value you see on the form
    innovatorId: '',
    username: '',
    firstName: '',
    lastName: '',
    avatarUrl: '',
    email: '',
    occupation: '',
    updatedBy: '',
    updatedOn: new Date(),
    tags: []
  }

  comment: Comment = {
    commentator_name: '',
    comment_data: '',
    dateOfComment: new Date()
  }

  supportingFile: Files = {
    fileName:'',
    extension:'',
    content:'',
    fileType:''
  }

  newInnovationValue: Innovation = {
    innovationId: "",
    innovationName: "",
    innovatorId: "",
    name: "",
    email: "",
    innovationDesc: "",
    challenges: "",
    domain: "",
    status: "",
    rating: null,
    // List<String> stakeholders;
    dateOfCreation: new Date(),
    comments: [],
    coverPhoto: {
      fileName: "",
      extension:"",
      content:"",
      fileType: ""
    },
    uploadedFiles: []
  }

  constructor(private fb: FormBuilder, private cookie: CookiesService, private server: ServerService, private _snackBar: MatSnackBar
    , private router: Router, private activatedroute: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.activatedroute.snapshot.paramMap.get('innovationId') as string;
    if(this.id){
      this.getInnovationById()
    }
  }
  
  getInnovationById(){
    this.server.getInnovationById(this.id).subscribe(data => {
      this.newInnovationValue = data;
      this.newInnovation.controls['innovationName'].setValue(this.newInnovationValue.innovationName);
      this.newInnovation.controls['innovationDesc'].setValue(this.newInnovationValue.innovationDesc);
      this.newInnovation.controls['challenges'].setValue(this.newInnovationValue.challenges);
      this.newInnovation.controls['domain'].setValue(this.newInnovationValue.domain);
      this.newInnovation.controls['status'].setValue(this.newInnovationValue.status);
      this.addedFiles = this.newInnovationValue.uploadedFiles;
      this.tempFileArray = [...this.newInnovationValue.uploadedFiles];
    });
  }

  onChange(event: any) {
    this.imageFile = event.target.files[0];
    let filereader = new FileReader();
    filereader.readAsDataURL(this.imageFile);
    filereader.onload = () => {
      this.image = filereader.result;
      this.newInnovationValue.coverPhoto.fileName = this.imageFile.name.split(".")[0];
    this.newInnovationValue.coverPhoto.extension = this.imageFile.name.split(".")[1];
    this.newInnovationValue.coverPhoto.content = this.image;
    this.newInnovationValue.coverPhoto.fileType = "";
    };
  }

  tempFileArray:any = [];
  onChanges(event: any) {
    this.docFile = event.target.files[0];
    let filereader = new FileReader();
    filereader.readAsDataURL(this.docFile);
    filereader.onload = () => {
      this.supportingFile.content = filereader.result as string;
      if(this.newInnovation.controls['fileTitle'].value)
        this.supportingFile.fileName = this.newInnovation.controls['fileTitle'].value;
      else
        this.supportingFile.fileName = this.docFile.name.split(".")[0];
      this.supportingFile.extension = this.docFile.name.split(".")[1];
      this.supportingFile.fileType = "testing";
    };
  }

  reset() {
    this.tempFileArray.push(Object.assign({},this.supportingFile));
    this.docFile = null;
    this.newInnovation.controls['docFiles'].reset();
   this.newInnovation.controls['fileTitle'].reset();
  }
  remove(i:number){
    this.tempFileArray.splice(i,1)
  }

  removeCoverImage(){
    this.newInnovationValue.coverPhoto.fileName = null;
    this.newInnovationValue.coverPhoto.fileType = null;
    this.newInnovationValue.coverPhoto.extension = null;
    this.newInnovationValue.coverPhoto.content = null;
  }

  onSubmit(): void {
    this.cookie.isInnovatorLoggedIn = true;
    this.innovatorValue = this.cookie.login();
    this.newInnovationValue.innovationName = this.newInnovation.controls['innovationName'].value;
    this.newInnovationValue.innovatorId = this.innovatorValue.innovatorId;
    this.newInnovationValue.email = this.innovatorValue.email;
    this.newInnovationValue.name = this.innovatorValue.firstName + " " + this.innovatorValue.lastName;
    this.newInnovationValue.innovationDesc = this.newInnovation.controls['innovationDesc'].value;
    this.newInnovationValue.challenges = this.newInnovation.controls['challenges'].value;
    this.newInnovationValue.domain = this.newInnovation.controls['domain'].value;
    this.newInnovationValue.status = this.newInnovation.controls['status'].value;

    this.newInnovationValue.uploadedFiles = this.tempFileArray;
    if(this.id){
      this.newInnovationValue.dateOfCreation = new Date();
      this.server.updateInnovation(this.newInnovationValue).subscribe();
    }
    else{
      this.server.addInnovation(this.newInnovationValue).subscribe((data)=>{
      });
    }
    
    this.router.navigateByUrl("innovators");
  }

  cancelForm(){
    this.newInnovation.reset();
    this.docFile = null;
    this.newInnovation.controls['docFiles'].reset();
    this.newInnovation.controls['fileTitle'].reset();
    this.tempFileArray = [];
    this.newInnovationValue.coverPhoto.content = null;
    this.newInnovationValue.coverPhoto.extension = null;
    this.newInnovationValue.coverPhoto.fileName = null;
    this.newInnovationValue.coverPhoto.fileType = null;
    this.router.navigateByUrl("innovators")
  }
}
