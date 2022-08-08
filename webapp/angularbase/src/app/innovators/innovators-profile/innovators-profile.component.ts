import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookiesService } from 'src/app/services/cookies.service';
import { ServerService } from 'src/app/services/server.service';
import { Innovator } from 'src/app/innovator';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { ENTER, COMMA } from '@angular/cdk/keycodes';

@Component({
  selector: 'app-innovators-profile',
  templateUrl: './innovators-profile.component.html',
  styleUrls: ['./innovators-profile.component.css'],
})
export class InnovatorsProfileComponent implements OnInit {
  constructor(
    private cookies: CookiesService,
    private serverService: ServerService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  cookieValue!: Innovator; //value you get from the cookie

  innovatorValue: Innovator = {
    //value you see on the form
    innovatorId: '',
    username: '',
    firstName: '',
    lastName: '',
    avatarUrl: '',
    email: '',
    occupation: '',
    updatedBy: '',
    updatedOn: new Date(),
    tags: [],
  };

  innovatorForm = this.fb.group({
    innovatorId: [{ value: '', disabled: true }, Validators.required],
    username: [{ value: '', disabled: true }, Validators.required],
    firstName: [{ value: '' }, Validators.required],
    lastName: [{ value: '' }, Validators.required],
    avatarUrl:[{value:''}, Validators.required],
    email: [{ value: '', disabled: true }, Validators.required],
    occupation: [{ value: '' }],
    updatedBy: [{ value: '', disabled: true }],
    updatedOn: [{ value: '', disabled: true }],
    tags: [] 
  });

  ngOnInit() {
    this.cookieValue = this.cookies.login();
    this.serverService
      .getInnovator(this.cookieValue.innovatorId)
      .subscribe((data) => {
        this.innovatorValue = data;
        this.innovatorForm.patchValue(data);        
      });
  }

  ngOnChanges() {
    this.serverService
      .getInnovator(this.cookieValue.innovatorId)
      .subscribe((data) => {
        this.innovatorValue = data;
        this.innovatorForm.patchValue(data);
      });
  }

  onSubmit() {
    this.innovatorValue.firstName = this.innovatorForm.get('firstName')?.value;
    this.innovatorValue.lastName = this.innovatorForm.get('lastName')?.value;
    this.innovatorValue.occupation =
    this.innovatorForm.get('occupation')?.value;
    this.innovatorValue.updatedBy = this.innovatorForm.get('username')?.value;
    this.innovatorValue.updatedOn = new Date();
    this.serverService
      .updateInnovator(this.innovatorValue)
      .subscribe((data) => {
      });
      this.isEditing = false;
  }


  isEditing:boolean=false;

  test(){
  }

  edit(){
    this.isEditing = true;
  }

  cancel(){
    // this.innovatorForm.reset();
    this.isEditing = false;
  }

  close(){
    this.router.navigateByUrl("innovators");
  }

  //tags
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    if (value) {
      
      if(this.innovatorValue.tags==null){
        this.innovatorValue.tags = new Array('');
        this.innovatorValue.tags[0]= value;
      }
      else{
        this.innovatorValue.tags?.push(value);
      }
      
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removeTag(j:number): void {
    this.innovatorValue.tags?.splice(j,1);
  }
}

