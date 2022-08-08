import { PrivateComment } from './../../private-comment';
import { Comment } from 'src/app/comment';
import { Attachment } from './../../attach';
import { ServerService } from 'src/app/services/server.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ApiService } from './../../services/api.service';
import { Proposal } from './../../proposal';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Innovation } from 'src/app/innovation';
import { CookiesService } from 'src/app/services/cookies.service';
import { Innovator } from 'src/app/innovator';
import * as lodash from "lodash";

@Component({
  selector: 'app-view-proposal',
  templateUrl: './view-proposal.component.html',
  styleUrls: ['./view-proposal.component.css']
})
export class ViewProposalComponent implements OnInit {


  constructor(private activatedRoute: ActivatedRoute, private apiservce: ApiService,
    private sanitizer: DomSanitizer, private server: ServerService, private cookies: CookiesService, private apiService: ApiService) { }

  proposalId: string = "";
  selectedProposal: Proposal = {
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
      content: "",
      fileName: "",
      extension: "",
      fileType: ""
    },
    attachment: [],
    privateComments: []
  };

  fileUrl: any;
  currentInnovation!: Innovation;
  comment: string = "";
  allComments: Comment[] = [];
  cookieValue!: Innovator;
  commentObj: PrivateComment = {
    commentator: null,
    content: null,
    commentDate: null,
    avatarUrl: null
  }
  proposalCount!: number;
  proposalIndex!: number;
  commentsLength!: number;
  allPrivateComments: PrivateComment[] = [];

  ngOnInit(): void {
    this.proposalId = this.activatedRoute.snapshot.paramMap.get('proposalId') as string;
    this.proposalCount = this.activatedRoute.snapshot.paramMap.get('proposalsCount') as unknown as number;
    this.proposalIndex = this.activatedRoute.snapshot.paramMap.get('index') as unknown as number;
    this.proposalIndex = this.proposalIndex;
    this.apiservce.getProposalById(this.proposalId).subscribe(data => {
      this.selectedProposal = data;
      this.server.getInnovationById(this.selectedProposal.innovationId).subscribe(data => {
        this.currentInnovation = data;
        this.commentsLength = this.currentInnovation.comments.length;
        this.allComments = this.currentInnovation.comments.slice(this.commentsLength - 5, this.commentsLength);
        this.commentsLength = this.commentsLength - 5;

        if (this.selectedProposal.privateComments) {
          this.allPrivateComments = this.sortCommentsInLatestOrder([...this.selectedProposal.privateComments])
          // this.allPrivateComments = [...this.selectedProposal.privateComments].reverse();
          console.log(this.allPrivateComments);
        }
      });

    })
    this.cookieValue = this.cookies.login();
    // const moment = require('moment');
    // let date;
    // if(this.currentInnovation.dateOfCreation)
    //   date = new Date(this.currentInnovation.dateOfCreation);
    //   console.log(moment(date).fromNow());

  }

  sortCommentsInLatestOrder(commentsList: any) {
    const commentsUpdatedTSList = commentsList.map((cmt: any) => {
      const commentUpdated = Object.assign({}, cmt, {
        dateTS: new Date(cmt.commentDate).getTime() / 1000,
      });
      return commentUpdated;
    });
    return lodash.reverse(lodash.sortBy(commentsUpdatedTSList, ["dateTS"]));
  }

  viewdoc(content: string) {
    const data = atob(content.substring(content.indexOf(',') + 1));
    const blob = new Blob([data], { type: 'application/octet-stream' });

    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }

  decline() {
    this.selectedProposal.statusOfProposal = "declined";
    this.apiservce.updateProposal(this.selectedProposal).subscribe();
  }

  accept() {
    this.selectedProposal.statusOfProposal = "accepted";
    this.apiservce.updateProposal(this.selectedProposal).subscribe();
  }

  addComment() {
    this.commentObj.content = this.comment;
    this.commentObj.commentDate = new Date();
    this.commentObj.commentator = this.cookieValue.firstName + " " + this.cookieValue.lastName;
    this.commentObj.avatarUrl = this.cookieValue.avatarUrl;

    let tempData: PrivateComment[] = []
    tempData = JSON.parse(JSON.stringify(this.allPrivateComments))
    tempData.push(this.commentObj);

    this.selectedProposal.privateComments = this.sortCommentsInLatestOrder(tempData)

    this.apiService.updateProposal(this.selectedProposal).subscribe((data) => {
      this.comment = "";
      this.allPrivateComments = this.sortCommentsInLatestOrder(tempData)
    });

  }

  showMore() {
    for (let i = this.commentsLength - 5; i < this.commentsLength; i++) {
      this.allComments.push(this.currentInnovation.comments[i]);
    }
    this.commentsLength = this.commentsLength - 5;
  }

}
