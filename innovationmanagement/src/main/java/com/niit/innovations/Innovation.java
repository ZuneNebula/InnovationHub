package com.niit.innovations;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Innovation {
    @Id
    private String innovationId;
    private String innovationName;
    private String innovatorId;
    private String name;
    private String email;
    private String innovationDesc;
    private String challenges;
    private String domain;
    private String status;
    private float rating;
    //private List<String> stakeholders;
    private String dateOfCreation;
    private List<Comment> comments;
    private Files coverPhoto;
    private List<Files> uploadedFiles;

    public Innovation(String innovationId, String innovationName, String innovatorId, String name, String email, String innovationDesc, String challenges, String domain, String status, float rating, String dateOfCreation,
                      Files coverPhoto,List<Files> uploadedFiles) {
        this.innovationId = innovationId;
        this.innovationName = innovationName;
        this.innovatorId = innovatorId;
        this.name = name;
        this.email = email;
        this.innovationDesc = innovationDesc;
        this.challenges = challenges;
        this.domain = domain;
        this.status = status;
        this.rating = rating;
        this.dateOfCreation = dateOfCreation;
        this.coverPhoto = coverPhoto;
        this.uploadedFiles = uploadedFiles;
    }

    public Innovation(){}

}
