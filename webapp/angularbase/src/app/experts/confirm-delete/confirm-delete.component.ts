import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Proposal } from 'src/app/proposal';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html',
  styleUrls: ['./confirm-delete.component.css']
})
export class ConfirmDeleteComponent implements OnInit {

  constructor(private api:ApiService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogComp:MatDialogRef<ConfirmDeleteComponent>) { }

  proposalData:any={};

  ngOnInit(): void {
    const getProposalById=this.api.getProposalById(this.data.proposalId);
    getProposalById.subscribe((proposal)=>{
      this.proposalData=proposal;
    })
  }

  proposalToBeDeleted!:Proposal;

  delete(id:string){
    const deleteObservable = this.api.deleteProposalById(id);
    deleteObservable.subscribe((data) => {
      this.proposalToBeDeleted = data;
      this.dialogComp.close(id);
      // window.location.reload();
    })
  }

}
