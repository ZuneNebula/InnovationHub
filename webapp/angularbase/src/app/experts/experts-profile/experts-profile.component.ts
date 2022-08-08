import { Component, OnInit } from '@angular/core';
import { CookiesService } from 'src/app/services/cookies.service';
import { Expert } from 'src/app/expert';
import { ServerService } from 'src/app/services/server.service';
import { Router } from '@angular/router';
import {
  FormArray,
  FormBuilder,
  FormControl,
  Validators,
} from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { ENTER, COMMA } from '@angular/cdk/keycodes';

export interface Fruit {
  name: string;
}

@Component({
  selector: 'app-experts-profile',
  templateUrl: './experts-profile.component.html',
  styleUrls: ['./experts-profile.component.css'],
})
export class ExpertsProfileComponent implements OnInit {
  constructor(
    private cookies: CookiesService,
    private serverService: ServerService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  cookieValue!: Expert; //value from cookie

  expertValue: Expert = {
    //value you see on the form
    expertId: '',
    username: '',
    firstName: '',
    lastName: '',
    avatarUrl: '',
    email: '',
    occupation: '',
    updatedBy: '',
    updatedOn: new Date(),
    tags: [],
    specialization: [],
    rating: 0.0,
  };

  expertForm = this.fb.group({
    expertId: [{ value: '', disabled: true }, Validators.required],
    username: [{ value: '', disabled: true }, Validators.required],
    avatarUrl:[{value:''}, Validators.required],
    firstName: [{ value: '' }, Validators.required],
    lastName: [{ value: '' }, Validators.required],
    email: [{ value: '', disabled: true }, Validators.required],
    occupation: [{ value: '' }],
    updatedBy: [{ value: '', disabled: true }],
    updatedOn: [{ value: '', disabled: true }],
    specialization: [{value:[]}],
    tags:[],
    rating: [{ value: 0, disabled: true }]
  });


  domains = [
    { name: 'Any'},
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

  onChange(event:any) {
    this.expertValue.specialization.push(event.value);
    this.expertForm.controls['specialization'].reset();
  }

  remove(i:number){
    this.expertValue.specialization.splice(i,1);
  }

  ngOnInit(): void {
    this.cookieValue = this.cookies.login();
    this.serverService
      .getExpert(this.cookieValue.expertId)
      .subscribe((data) => {
        this.expertValue = data;
        this.expertForm.patchValue(data);
      });
  }

  ngOnChanges(): void {
    this.serverService
      .getExpert(this.cookieValue.expertId)
      .subscribe((data) => {
        this.expertValue = data;
        this.expertForm.patchValue(data);
      });
  }

  onSubmit() {
    this.expertValue.firstName = this.expertForm.get('firstName')?.value;
    this.expertValue.lastName = this.expertForm.get('lastName')?.value;
    this.expertValue.occupation = this.expertForm.get('occupation')?.value;
    this.expertValue.updatedBy = this.expertForm.get('username')?.value;
    this.expertValue.updatedOn = new Date();
    if(this.expertValue.specialization.length==0){
      this.expertValue.specialization.push(this.domains[0].name);
    }

    this.serverService.updateExpert(this.expertValue).subscribe((data) => {
    });
    this.isEditing = false;
  }

  isEditing: boolean = false;

  test() {
  }

  edit() {
    this.isEditing = true;
  }

  cancel() {
    this.isEditing = false;
  }

  close(){
    this.router.navigateByUrl("experts");
  }

  //tags
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    if (value) {
      
      if(this.expertValue.tags==null){
        this.expertValue.tags = new Array('');
        this.expertValue.tags[0]= value;
      }
      else{
        this.expertValue.tags?.push(value);
      }
      
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removeTag(j:number): void {
    this.expertValue.tags?.splice(j,1);
  }
}